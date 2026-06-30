package model;

public enum EstagioDespesa {

    EMPENHADO,
    PAGO;

    public static EstagioDespesa fromString(String valor) {
        if (valor == null) return null;

        if (valor.equalsIgnoreCase("Despesas Empenhadas")) {
            return EMPENHADO;
        }

        if (valor.equalsIgnoreCase("Despesas Pagas")) {
            return PAGO;
        }

        return null;
    }
}