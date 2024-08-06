package com.example.ecommerceapi.Service;

import com.example.ecommerceapi.model.ProductReviews;
import com.example.ecommerceapi.model.User;
import com.example.ecommerceapi.model.WishList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final ProductService productService;
    private final MerchantStockService mStockService;
    private final MerchantService merchantService;
    private final CategoryService categoryService;
    ArrayList<User> users = new ArrayList<User>();
    ArrayList<WishList> wishLists =new ArrayList<>();
    ArrayList<ProductReviews> productReviewsList =new ArrayList<>();
    public ArrayList<User> getAllUsers() {
        return users.isEmpty()?null:users;
    }

    public void addUser(User user) {
        users.add(user);
    }
   
    public boolean removeUser(int id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateUser(int id, User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }
//    Create endpoint where user can add more stocks of product to a merchantStock
//• this endpoint should accept a product id and merchant id and the amount of additional
//stock.
    public boolean addProductToStock(int mID, int pID, int mStockID, int quantity) {
        for (int k=0; k<merchantService.getAllMerchants().size();k++){
            if (merchantService.merchants.get(k).getId() == mID){
                for (int i = 0; i < productService.getProducts().size(); i++) {
                    if (productService.getProducts().get(i).getId()==pID){
                        for (int j = 0; j < mStockService.getAllMerchantStocks().size(); j++) {
                            if (mStockService.getAllMerchantStocks().get(j).getId()==mStockID){
                                mStockService.getAllMerchantStocks().get(j).setStock(mStockService.getAllMerchantStocks().get(j).getStock()+quantity);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

//• Add 3 extra endpoints to enhance your e-commerce website.
    public int buyProduct(int uID, int pID, int mID) {
        for (int i=0; i<users.size(); i++) {
            if (users.get(i).getId()==uID){
                for (int j=0; j<productService.getProducts().size(); j++) {
                    if (productService.getProducts().get(j).getId()==pID){
                        if (users.get(i).getBalance()<productService.products.get(j).getPrice()){
                            return 1;
                        }else {
                            for (int k=0; k<mStockService.getAllMerchantStocks().size(); k++) {
                                if (mStockService.getAllMerchantStocks().get(k).getId()==mID){
                                    if (mStockService.getAllMerchantStocks().get(k).getStock()<1){
                                        return 2;
                                    }else {
                                        users.get(i).setBalance(users.get(i).getBalance()-productService.getProducts().get(j).getPrice());
                                        mStockService.getAllMerchantStocks().get(k).setStock(mStockService.getAllMerchantStocks().get(k).getStock()-1);
                                        return 3;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return 4;
    }

    public boolean checkIsAdmin(int uID) {
        for (int i=0; i<users.size(); i++) {
            if (users.get(i).getId()==uID && users.get(i).getRole().equalsIgnoreCase("admin")){
                return true;
            }
        }
        return false;
    }

    public ArrayList getTheProductForAdmin(boolean displayP) {
        if (displayP) {
            return productService.getProducts();
        }
        return null;
    }

    public boolean addWishList(int uID,int pID) {
        for (int i=0; i<productService.getProducts().size(); i++) {
            if (productService.getProducts().get(i).getId()==pID){
                for (int j=0; j<mStockService.getAllMerchantStocks().size(); j++) {
                    wishLists.add(new WishList(uID,productService.getProducts().get(i).getName(),mStockService.getAllMerchantStocks().get(j).getStock(),productService.getProducts().get(i).getPrice()));
                    return true;
                }
            }
        }
        return false;
    }
    public ArrayList getWishList(int uID) {
        ArrayList getInfo=new ArrayList();
        for (int i=0; i<wishLists.size(); i++) {
            if (wishLists.get(i).getCustomerId()==uID){
                for (User user:users) {
                    if (user.getId()==uID){
                        getInfo.add(user.getUsername());
                    }
                }
                getInfo.add(wishLists.get(i));
            }
        }
        return getInfo.isEmpty()?null:getInfo;
    }

    //2
    public boolean addReviewForProduct(ProductReviews productReviews) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == productReviews.getUser_id()) {
                for (int j = 0; j < productService.getProducts().size(); j++) {
                    if (productService.getProducts().get(j).getId() == productReviews.getProduct_id()) {
                        productReviewsList.add(productReviews);
                        return true;
                    }
                }
            }
        }
        return false;
    }
//1
    public ArrayList deleteProductByAdmin(int pID, boolean isDelete) {
        ArrayList getAllDAboutProduct = new ArrayList<>();

        for (int j = 0; j < productService.getProducts().size(); j++) {

            if (productService.getProducts().get(j).getId() == pID && categoryService.getAllCategories().get(j).getId() == productService.getProducts().get(j).getCategoryID()) {

                for (int k = 0; k < merchantService.getAllMerchants().size(); k++) {

                    if (merchantService.getAllMerchants().get(k).getId() == mStockService.getAllMerchantStocks().get(k).getId()) {

                        getAllDAboutProduct.add(merchantService.getAllMerchants().get(k).getName());

                        for (int l = 0; l < productReviewsList.size(); l++) {

                            if (productReviewsList.get(l).getProduct_id() == productService.getProducts().get(k).getId()) {
                                getAllDAboutProduct.add(productService.getProducts().get(j).getName());
                                getAllDAboutProduct.add(productService.getProducts().get(j).getPrice());
                                getAllDAboutProduct.add(categoryService.getAllCategories().get(j).getName());
                                getAllDAboutProduct.add(productReviewsList.get(l));

                                if(isDelete){
                                    removeByAdmin(j,l,k);
                                    return getAllDAboutProduct;
                                }else {
                                    return getAllDAboutProduct;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public void removeByAdmin(int pIndex,int pReviewIndex,int mIndex){
        productReviewsList.remove(pReviewIndex);
        mStockService.getAllMerchantStocks().remove(mIndex);
        merchantService.getAllMerchants().remove(mIndex);
        productService.getProducts().remove(pIndex);
    }
    public boolean addToCart(int pID,int uID){
        for (User user : users) {
            if (user.getId() == uID) {
                for (int j = 0; j < productService.getProducts().size(); j++) {
                    if (productService.getProducts().get(j).getId() == pID) {
                        user.getProductsCart().add(productService.products.get(j));
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean removeCart(int pID,int uID){
        for (User user : users) {
            if (user.getId() == uID) {
                for (int j = 0; j < productService.getProducts().size(); j++) {
                    if (productService.getProducts().get(j).getId() == pID) {
                        user.getProductsCart().remove(productService.products.get(j));
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
