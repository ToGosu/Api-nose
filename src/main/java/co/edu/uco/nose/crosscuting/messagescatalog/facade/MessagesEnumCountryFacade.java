package co.edu.uco.nose.crosscuting.messagescatalog.facade;

import co.edu.uco.nose.crosscuting.helper.TextHelper;

public enum MessagesEnumCountryFacade {

    FIND_ALL_COUNTRIES_UNEXPECTED_ERROR(
            "Error al consultar la información de los países.", // title
            "Se ha presentado un error inesperado al consultar los países: " // content
    ),
    FIND_COUNTRIES_BY_FILTER_UNEXPECTED_ERROR(
            "Error al consultar la información de los países con los filtros suministrados.", // title
            "Se ha presentado un error inesperado al consultar los países con los filtros suministrados: " // content
    ),
    FIND_SPECIFIC_COUNTRY_UNEXPECTED_ERROR(
            "Error al consultar la información del país solicitado.", // title
            "Se ha presentado un error inesperado al consultar el país solicitado: " // content
    );

    private String title;
    private String content;

    private MessagesEnumCountryFacade(final String title, final String content) {
        setTitle(title);
        setContent(content);
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = TextHelper.getDefaultWithTrim(title);
    }

    public String getContent() {
        return content;
    }

    private void setContent(String content) {
        this.content = TextHelper.getDefaultWithTrim(content);
    }
}