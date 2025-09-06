package stellarburgers.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResponse {
    private boolean success;
    private String name;
    private OrderInfo order;
    private String message;

    public OrderResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderInfo getOrder() {
        return order;
    }

    public void setOrder(OrderInfo order) {
        this.order = order;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OrderInfo {
        private Integer number;
        private List<Object> ingredients; // Добавляем поле ingredients

        public OrderInfo() {
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public List<Object> getIngredients() {
            return ingredients;
        }

        public void setIngredients(List<Object> ingredients) {
            this.ingredients = ingredients;
        }
    }
}