package co.edu.uco.nose.crosscuting.messagescatalog.facade;

import co.edu.uco.nose.crosscuting.helper.TextHelper;

public enum MessagesEnumStateFacade {

    FIND_ALL_STATES_UNEXPECTED_ERROR(
            "Error al consultar la información de los estados.", // title
            "Se ha presentado un error inesperado al consultar los estados: " // content
    ),
    FIND_STATES_BY_FILTER_UNEXPECTED_ERROR(
            "Error al consultar la información de los estados con los filtros suministrados.", // title
            "Se ha presentado un error inesperado al consultar los estados con los filtros suministrados: " // content
    ),
    FIND_SPECIFIC_STATE_UNEXPECTED_ERROR(
            "Error al consultar la información del estado específico.", // title
            "Se ha presentado un error inesperado al consultar el estado específico: " // content
    );

    private String title;
    private String content;

    private MessagesEnumStateFacade(final String title, final String content) {
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