import java.io.Serializable;

public class Subscription implements Serializable {

    private int installFee ; //Fixed fee : 10
    private  int numberTV;
    private Subscriber subscriber;
    private SubscriptionCycle cycle;
    private String date;

    //Total fee
    private int totalFee;

    public Subscription(int numberTV, Subscriber subscriber, SubscriptionCycle cycle, String date) {
        this.numberTV = numberTV;
        this.subscriber = subscriber;
        this.cycle = cycle;
        this.date = date;

        this.installFee = numberTV * 10;
    }

    public int getNumberTV() {
        return numberTV;
    }

    public void setNumberTV(int numberTV) {
        this.numberTV = numberTV;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public SubscriptionCycle getCycle() {
        return cycle;
    }

    public void setCycle(SubscriptionCycle cycle) {
        this.cycle = cycle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalFee() {
        totalFee = installFee + 5;
        return totalFee;
    }

//    public void setTotalFee(int totalFee) {
//        this.totalFee = totalFee;
//    }

    @Override
    public String toString() {
        return "Subscription{" +
                "installFee=" + installFee +
                ", numberTV=" + numberTV +
                ", subscriber=" + subscriber +
                ", cycle=" + cycle +
                ", date='" + date + '\'' +
                ", totalFee=" + totalFee +
                '}';
    }



}
