package implement;

import data.DataURL;
import interface1.IBread;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Topping implements IBread, Serializable {
    private static final long serialVersionUID = -1687676336684729039L;
    private int toppingId;
    private String toppingName;
    private Boolean toppingStatus;
    private float exportPrice;

    public Topping() {
    }

    public Topping(int toppingId, String toppingName, Boolean toppingStatus, float exportPrice) {
        this.toppingId = toppingId;
        this.toppingName = toppingName;
        this.toppingStatus = toppingStatus;
        this.exportPrice = exportPrice;
    }

    public int getToppingId() {
        return toppingId;
    }

    public void setToppingId(int toppingId) {
        this.toppingId = toppingId;
    }

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    public Boolean getToppingStatus() {
        return toppingStatus;
    }

    public void setToppingStatus(Boolean toppingStatus) {
        this.toppingStatus = toppingStatus;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }




    @Override
    public void inputData1(Scanner sc, List<Topping> list) {
        System.out.println("Nhâp tên Topping: ");
        this.toppingName = sc.nextLine();
        System.out.println("Nhập giá bán Topping: ");
        this.exportPrice = Float.parseFloat(sc.nextLine());
        System.out.println("Nhập trạng thái: ");
        this.toppingStatus = Boolean.parseBoolean(sc.nextLine());
    }

    @Override
    public void displayData() {
        System.out.println("-----------------");
        System.out.printf("Mã danh mục là: %d\n",toppingId);
        System.out.printf("Tên danh mục là: %s\n",toppingName);
        System.out.printf("Giá bán Topping: %.1f\n",exportPrice);
        System.out.printf("Trạng thái: " + ((toppingStatus) ? "Còn"+ "\n" : "Không còn" + "\n"));
        System.out.println("-----------------");
    }

    @Override
    public boolean create1(Topping topping) {
        return false;
    }

    @Override
    public List<Topping> readFromFileTopping() {
        List<Topping> listTopping = new ArrayList<>();
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            file = new File(DataURL.URL_TOPPING_FILE);
            if (file.exists()) {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                listTopping = (List<Topping>) ois.readObject();
            } else {
                return listTopping;
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
        return listTopping;
    }

    @Override
    public String toString() {
        return "Topping{" +
                "toppingId=" + toppingId +
                ", Tên Topping ='" + toppingName + '\'' +
                ", Trạng thái Topping =" + toppingStatus +
                ", Giá Topping =" + exportPrice +
                '}';
    }

    @Override
    public boolean writeToFileTopping(List<Topping> list) {
        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean returnData = true;
        try {
            file = new File(DataURL.URL_TOPPING_FILE);
            if (!file.exists()) {
                file.createNewFile();
            }
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
    public List<Bread> readFormFileBread() {
        return null;
    }

    @Override
    public boolean writeFormFileBread(List<Bread> list) {
        return false;
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
