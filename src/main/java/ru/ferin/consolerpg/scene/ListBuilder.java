package ru.ferin.consolerpg.scene;

public class ListBuilder {
    private int num = 1;
    private StringBuilder sb = new StringBuilder();

    /**
     * Добавить пункт в список
     * @param text описание
     */
    public ListBuilder add(String text) {
        sb.append(num).append(". ").append(text).append("\n");
        num++;
        return this;
    }

    /**
     * Возвращает список
     * @return текст списка
     */
    public String build() {
        return sb.toString();
    }

    /**
     * Возвращает список и очищает его
     * @return текст списка
     */
    public String buildс() {
        String str = sb.toString();
        sb = new StringBuilder();
        return str;
    }
}
