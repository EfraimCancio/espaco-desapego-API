package desapego.brecho.api.domain.origin;


public enum Type {
    G("Garimpo"),
    D("Doação"),
    C("Consignado");

    private final String value;

    Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

