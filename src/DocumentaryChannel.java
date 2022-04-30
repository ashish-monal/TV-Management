public class DocumentaryChannel  extends TVChannel{
    int additionalFee =12;

    public DocumentaryChannel(String channelName, String language, String category, int price) {
        super(channelName, language, category, price);
    }

    @Override
    public int getPrice() {
        return super.getPrice()+additionalFee;
    }
}
