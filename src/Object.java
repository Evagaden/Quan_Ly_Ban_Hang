
import java.io.IOException;
import java.io.File;
public class Object {
    private String name;
    private String id;
    private int quantity;
    private String unit;
    private int bid_prices;
    private int ask_prices;

    private String pathPic;
    public Object(String name, String id, int quantity,String unit, int bid_prices, int ask_prices, String pathPic) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
        this.unit = unit;
        this.bid_prices = bid_prices;
        this.ask_prices = ask_prices;
        this.pathPic = pathPic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBid_prices() {
        return bid_prices;
    }

    public void setBid_prices(int bid_prices) {
        this.bid_prices = bid_prices;
    }

    public int getAsk_prices() {
        return ask_prices;
    }

    public void setAsk_prices(int ask_prices) {
        this.ask_prices = ask_prices;
    }

    public String getPathPic()
    {
        return pathPic;
    }

    public void setPathPic(String pathPic)
    {
        this.pathPic = pathPic;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
