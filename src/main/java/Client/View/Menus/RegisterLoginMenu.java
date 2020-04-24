package Client.View.Menus;

import Client.Controller.LoginRegisterController;
import Client.Controller.UserSectionController.UserSectionController;
import Client.Models.Person.Buyer;
import Client.Models.Person.Manager;
import Client.Models.Person.Person;
import Client.Models.Person.Seller;
import Client.View.Menus.UserSectionMenus.BuyerAccount.BuyerSection;
import Client.View.Menus.UserSectionMenus.ManagerSection;
import Client.View.Menus.UserSectionMenus.SellerSection;
import Client.View.Menus.UserSectionMenus.UserSection;
import Server.Controller.LoginRegisterServerController;

public class RegisterLoginMenu extends Menu {
    public RegisterLoginMenu(Menu superMenu,String name) {
        super(superMenu, name);
        this.addSubMenu(addCreate());
        this.addSubMenu(addLogin());
        this.addSubMenu(addLogout());

    }

    @Override
    public void show() {
        if(UserSectionController.getLoggedInPerson()==null){
            System.out.println("Create\n" +
                    "login\n" +
                    "back");
        }
        else{
            System.out.println("Logout\n" +
                    "back");
        }
    }

    public Menu addCreate(){
        return new Menu(this, "Create") {
            String type;
            @Override
            public void show() {
                System.out.println("Which type of user do you want to be?(Manager|Seller|Buyer)");
            }

            @Override
            public void execute() {
                type=scanner.nextLine();
                if(type.equalsIgnoreCase("Manager")){
                    Manager manager=(Manager)makePerson(new Manager());
                    try {
                        LoginRegisterController.getInstance().createAccount(manager);
                        System.out.println("Welcome!\nplease login with your username and password");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());

                    }

                }else if(type.equalsIgnoreCase("Seller")){
                    Seller seller=(Seller)makePerson(new Seller());
                    System.out.println("What is your factory name?");
                    seller.setFactoryName(scanner.nextLine());
                    try {
                        LoginRegisterController.getInstance().createAccount(seller);
                        System.out.println("Welcome!\nplease login with your username and password");

                    } catch (Exception e) {
                        System.out.println(e.getMessage());

                    }
                }else if(type.equalsIgnoreCase("Buyer")){
                    Buyer buyer=(Buyer)makePerson(new Buyer());
                    try {
                        LoginRegisterController.getInstance().createAccount(buyer);
                        System.out.println("Welcome!\nplease login with your username and password");

                    } catch (Exception e) {
                        System.out.println(e.getMessage());

                    }
                }
                this.superMenu.show();
                this.superMenu.execute();

            }
            private Person makePerson(Person person){
                System.out.println("UserName:");
                person.setUserName(scanner.nextLine());
                System.out.println("Password:");
                person.setPassword(scanner.nextLine());
                System.out.println("What is your first name?");
                person.setFirstName(scanner.nextLine());
                System.out.println("What is your last name?");
                person.setLastName(scanner.nextLine());
                System.out.println("What is your email?");
                person.setEmail(scanner.nextLine());
                System.out.println("What is your phone number?");
                person.setPhoneNumber(scanner.nextLine());
                System.out.println("How much money do you have?");
                person.setCredit(Integer.parseInt(scanner.nextLine()));
                return person;
            }
        };
    }
    public Menu addLogin(){
        return new Menu(this, "login") {
            String userName;
            String password;
            @Override
            public void show() {
                System.out.println("UserName:");
            }

            @Override
            public void execute() {
                userName = scanner.nextLine();
                System.out.println("Password:");
                password = scanner.nextLine();

                try {
                    LoginRegisterController.getInstance().login(userName, password);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    this.superMenu.show();
                    this.superMenu.execute();
                }
                System.out.println("Welcome!");
                Person person = UserSectionController.getLoggedInPerson();
                if (person instanceof Manager) {
                    MainMenu.getInstance().addSubMenu(new ManagerSection(this));
                } else if (person instanceof Seller) {
                    MainMenu.getInstance().addSubMenu(new SellerSection(this));
                } else if (person instanceof Buyer) {
                    MainMenu.getInstance().addSubMenu(new BuyerSection(this));
                }


                this.superMenu.getSuperMenu().show();
                this.superMenu.getSuperMenu().execute();
            }
        };
    }
    public Menu addLogout(){
        return new Menu(this,"Logout") {
            @Override
            public void show() {
                System.out.println("GoodBye!");
            }

            @Override
            public void execute() {
                LoginRegisterController.getInstance().logout();
                MainMenu.getInstance().removeUserSection();
                this.superMenu.getSuperMenu().show();
                this.superMenu.getSuperMenu().execute();

            }
        };
    }

}
