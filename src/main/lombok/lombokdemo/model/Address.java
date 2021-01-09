package lombokdemo.model;

import lombok.*;


//More complex

@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class Address {
    private String street;
    private String flat_no;
    private int pincode;
    private String type;
}