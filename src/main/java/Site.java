public class Site {

    String id;
    String name;
    String country_id;
    String sale_fees_mode;
    int mercadopago_version;
    String default_currency_id;
    String immediate_payment;

    public Site(String id, String name, String country_id, String sale_fees_mode, int mercadopago_version, String default_currency_id, String immediate_payment) {
        this.id = id;
        this.name = name;
        this.country_id = country_id;
        this.sale_fees_mode = sale_fees_mode;
        this.mercadopago_version = mercadopago_version;
        this.default_currency_id = default_currency_id;
        this.immediate_payment = immediate_payment;
    }

}
