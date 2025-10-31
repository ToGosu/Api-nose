package co.edu.uco.nose.controller.dto;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.nose.crosscuting.helper.ObjectHelper;
import co.edu.uco.nose.crosscuting.helper.TextHelper;

public class Response<T> {

    private List<String> messages;
    private List<T> data;
    private boolean responseSucceded;

    // ✅ Constructor corregido
    public Response(final List<String> messages, final List<T> data, final boolean responseSucceded) {
        this.messages = ObjectHelper.getDefault(messages, new ArrayList<>());
        this.data = ObjectHelper.getDefault(data, new ArrayList<>());
        this.responseSucceded = responseSucceded;
    }

    // ✅ Fábricas seguras
    public static <T> Response<T> createSuccededResponse() {
        return new Response<>(new ArrayList<>(), new ArrayList<>(), true);
    }

    public static <T> Response<T> createFailedResponse() {
        return new Response<>(new ArrayList<>(), new ArrayList<>(), false);
    }

    public static <T> Response<T> createSuccededResponse(final List<T> data) {
        return new Response<>(new ArrayList<>(), data, true);
    }

    public static <T> Response<T> createFailedResponse(final List<T> data) {
        return new Response<>(new ArrayList<>(), data, false);
    }

    // ✅ Getters y Setters
    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(final List<String> messages) {
        this.messages = ObjectHelper.getDefault(messages, new ArrayList<>());
    }

    // ✅ Método seguro contra null
    public void addMessage(final String message) {
        if (messages == null) {
            messages = new ArrayList<>();
        }
        if (!TextHelper.isEmptyWithTrim(message)) {
            messages.add(message);
        }
    }

    public List<T> getData() {
        return data;
    }

    public void setData(final List<T> data) {
        this.data = ObjectHelper.getDefault(data, new ArrayList<>());
    }

    public boolean isResponseSucceded() {
        return responseSucceded;
    }

    public void setResponseSucceded(final boolean responseSucceded) {
        this.responseSucceded = responseSucceded;
    }
}
