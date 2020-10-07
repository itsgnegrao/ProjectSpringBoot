package itsgnegrao.ProjectSpringBoot.models;

import javax.validation.constraints.NotBlank;

public class ConsultaBody {

    @NotBlank
    String campo;

    @NotBlank
    String valor;

    public ConsultaBody(@NotBlank String campo, @NotBlank String valor) {
        this.campo = campo;
        this.valor = valor;
    }

    public ConsultaBody() {
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
