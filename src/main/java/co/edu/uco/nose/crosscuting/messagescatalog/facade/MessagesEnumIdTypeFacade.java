package co.edu.uco.nose.crosscuting.messagescatalog.facade;

import co.edu.uco.nose.crosscuting.helper.TextHelper;

public enum MessagesEnumIdTypeFacade {

    FIND_ALL_IDTYPES_UNEXPECTED_ERROR(
            "Error al consultar la información de los tipos de identificación.", // title
            "Se ha presentado un error inesperado al consultar los tipos de identificación: " // content
    ),
    FIND_IDTYPES_BY_FILTER_UNEXPECTED_ERROR(
            "Error al consultar la información de los tipos de identificación con los filtros suministrados.", // title
            "Se ha presentado un error inesperado al consultar los tipos de identificación con los filtros suministrados: " // content
    ),
    FIND_SPECIFIC_IDTYPE_UNEXPECTED_ERROR(
            "Error al consultar la información del tipo de identificación específico.", // title
            "Se ha presentado un error inesperado al consultar el tipo de identificación específico: " // content
    );

    private String title;
    private String content;

    private MessagesEnumIdTypeFacade(final String title, final String content) {
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