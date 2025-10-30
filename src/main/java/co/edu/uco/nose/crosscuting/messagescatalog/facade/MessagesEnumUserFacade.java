package co.edu.uco.nose.crosscuting.messagescatalog.facade;

import co.edu.uco.nose.crosscuting.helper.TextHelper;

public enum MessagesEnumUserFacade {

    REGISTER_USER_UNEXPECTED_ERROR(
            "Error al registrar la información del nuevo usuario. Por favor contacte al administrador del sistema.", // title
            "Se ha presentado un error inesperado al registrar la información del nuevo usuario. Por favor revise la traza completa del error para mayor detalle: " // content
    ),
    DROP_USER_UNEXPECTED_ERROR(
            "Error al eliminar la información del usuario. Por favor contacte al administrador del sistema.", // title
            "Se ha presentado un error inesperado al eliminar la información del usuario. Por favor revise la traza completa del error para mayor detalle: " // content
    ),
    UPDATE_USER_UNEXPECTED_ERROR(
            "Error al actualizar la información del usuario. Por favor contacte al administrador del sistema.", // title
            "Se ha presentado un error inesperado al actualizar la información del usuario. Por favor revise la traza completa del error para mayor detalle: " // content
    ),
    FIND_ALL_USERS_UNEXPECTED_ERROR(
            "Error al consultar la información de los usuarios. Por favor contacte al administrador del sistema.", // title
            "Se ha presentado un error inesperado al consultar la información de los usuarios. Por favor revise la traza completa del error para mayor detalle: " // content
    ),
    FIND_USERS_BY_FILTER_UNEXPECTED_ERROR(
            "Error al consultar la información de los usuarios por filtro. Por favor contacte al administrador del sistema.", // title
            "Se ha presentado un error inesperado al consultar la información de los usuarios por filtro. Por favor revise la traza completa del error para mayor detalle: " // content
    ),
    FIND_SPECIFIC_USER_UNEXPECTED_ERROR(
            "Error al consultar la información del usuario. Por favor contacte al administrador del sistema.", // title
            "Se ha presentado un error inesperado al consultar la información del usuario. Por favor revise la traza completa del error para mayor detalle: " // content
    );

    private String title;
    private String content;

    private MessagesEnumUserFacade(final String title, final String content) {
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