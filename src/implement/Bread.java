package implement;

import data.DataURL;
import interface1.IBread;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bread  implements IBread,Serializable {
    private static final long serialVersionUID = -8212683294804416601L;
    private static Topping toppingImp = new Topping();
    public static List<Topping> listTopping = toppingImp.readFromFileTopping();

    private int breadId;
    private String breadName;
    private String description;
    private float exportPrice;
    private float interest;
    private boolean breadStatus;
    private Topping topping;

    public Bread() {
    }

    public Bread(int breadId, String breadName, String description, float exportPrice, float interest, boolean breadStatus, Topping topping) {
        this.breadId = breadId;
        this.breadName = breadName;
        this.description = description;
        this.exportPrice = exportPrice;
        this.interest = interest;
        this.breadStatus = breadStatus;
        this.topping = topping;
    }

    public int getBreadId() {
        return breadId;
    }

    public void setBreadId(int breadId) {
        this.breadId = breadId;
    }

    public String getBreadName() {
        return breadName;
    }

    public void setBreadName(String breadName) {
        this.breadName = breadName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public boolean isBreadStatus() {
        return breadStatus;
    }

    public void setBreadStatus(boolean breadStatus) {
        this.breadStatus = breadStatus;
    }

    public Topping getTopping() {
        return topping;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }

    @Override
    public void inputData1(Scanner sc, List<Topping> list) {

        System.out.println("Nhâp tên danh mục sản phẩm: ");
        this.breadName = sc.nextLine();
        System.out.println("Nhâp mô tả cho sản phẩm: ");
        this.description = sc.nextLine();
        System.out.println("Nhập giá bán sản phẩm: ");
        this.exportPrice = Float.parseFloat(sc.nextLine());
        System.out.println("Nhập trạng thái: ");
        this.breadStatus = Boolean.parseBoolean(sc.nextLine());
        System.out.println("Nhập Topping sản phẩm: ");
        if (listTopping.size()==0){
            System.err.println("Không có Topping! Mời thêm topping trước");
            return;
        }
        for (Topping topping: listTopping) {
            topping.displayData();
        }
        listTopping = toppingImp.readFromFileTopping();
        int toppingId = Integer.parseInt(sc.nextLine());
        for (Topping topping: listTopping) {
            if(topping.getToppingId() == toppingId) {
                this.topping = topping;
                break;
            }
        }
    }

    @Override
    public void displayData() {
        System.out.println("--------------------------------");
        System.out.printf("Mã sản phẩm: %d\n", breadId);
        System.out.printf("Mô tả sản phẩm: %s\n", description);
        System.out.printf("Tên sản phẩm: %s\n", breadName);
        System.out.printf("Danh mục sản phẩm: %s\n", topping.getToppingName());
        System.out.printf("Giá bán sản phẩm: %.1f\n", exportPrice);
        System.out.printf("Trạng thái sản phẩm: " + ((breadStatus) ? "còn" + "\n" : "Không còn" + "\n"));
        System.out.println("--------------------------------");
    }

    @Override
    public boolean create1(Topping topping) {
        return false;
    }

    @Override
    public List<Topping> readFromFileTopping() {
        return null;
    }

    @Override
    public boolean writeToFileTopping(List<Topping> list) {
        return false;
    }

    @Override
    public List<Bread> readFormFileBread() {
        List<Bread> listBread = new ArrayList<>();
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            file = new File(DataURL.URL_BREAD_FILE);
            if (file.exists()) {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                listBread = (List<Bread>) ois.readObject();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
        return listBread;
    }

    @Override
    public boolean writeFormFileBread(List<Bread> list) {
        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean returnData = true;
        try {
            file = new File(DataURL.URL_BREAD_FILE);
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
        } catch (Exception ex) {
            returnData = false;
            ex.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
        return returnData;
    }

    @Override
    public List<Cart> readFormFileCart() {
        return null;
    }

    @Override
    public boolean writeFormFileCart(List<Cart> list) {
        return false;
    }



}
