package eapli.base.productmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
public class CategoryCode implements ValueObject, Comparable<CategoryCode> {
    private String code;

    public CategoryCode(final String code) {

        if (code == null || code.isBlank())
            throw new IllegalArgumentException("Category code cannot be empty!");
        String regex = "[a-zA-Z|0-9]{2,23}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(code);
        if (!matcher.matches())
            throw new IllegalArgumentException("Category code must be an alphanumeric code.");

        this.code = code;
    }

    public CategoryCode() {

    }

    @Override
    public String toString(){

        return code;
    }

    @Override
    public int compareTo(CategoryCode o) {
        return code.compareTo(o.code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryCode that = (CategoryCode) o;
        return code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
