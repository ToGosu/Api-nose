package co.edu.uco.nose.crosscuting.messagescatalog.facade;

import co.edu.uco.nose.crosscuting.helper.TextHelper;

public enum MessagesEnumCityFacade {

    FIND_ALL_CITIES_UNEXPECTED_ERROR(
            "Error al consultar la información de las ciudades.", // title (User Message)
            "Se ha presentado un error inesperado al consultar las ciudades: " // content (Technical Message)
    ),
    FIND_CITIES_BY_FILTER_UNEXPECTED_ERROR(
            "Error al consultar la información de las ciudades con los filtros suministrados.", // title
            "Se ha presentado un error inesperado al consultar las ciudades con los filtros suministrados: " // content
    ),
    FIND_SPECIFIC_CITY_UNEXPECTED_ERROR(
            "Error al consultar la información de la ciudad específica.", // title
            "Se ha presentado un error inesperado al consultar de la ciudad específica: " // content
    );

    private String title;
    private String content;

    private MessagesEnumCityFacade(final String title, final String content) {
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