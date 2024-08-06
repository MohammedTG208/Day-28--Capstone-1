I add in class User this attribute as ArrayList Take only Product object .
I add in service two method first one for delete cart and the 2nd add new one.
in controller I create two endpoint first one for delete and the 2nd for add cart.

===================================================

This is the code i add in classes
1-Class User:private ArrayList<Product> productsCart;
2-User Service:
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
    3-User Controller:
    @PostMapping("/add/to/cart/{pid}/{uid}")
    public ResponseEntity addToCart(@PathVariable int pid,@PathVariable int uid) {
        if (userService.addToCart(pid,uid)) {
            return ResponseEntity.status(200).body("You added to cart successfully");
        }
        return ResponseEntity.status(400).body("Check your input and try again");
    }
    @DeleteMapping("/delete/from/cart/{pid}/{uid}")
    public ResponseEntity deleteFromCart(@PathVariable int pid,@PathVariable int uid) {
        if (userService.addToCart(pid,uid)) {
            return ResponseEntity.status(200).body("You delete cart successfully");
        }
        return ResponseEntity.status(400).body("Check your input and try again");
    }
