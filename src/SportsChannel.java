public class SportsChannel  extends TVChannel{
    int additionalFee= 10;
    public SportsChannel (String channelName,String language, String category , int price)
    {
        super(channelName,language,category,price);
    }

    @Override
    public int getPrice() {
        return super.getPrice() +additionalFee;
    }
}
