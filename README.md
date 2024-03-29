# JPA

## @Enumerated(EnumType.STRING)
@Enumerated의; 기본타입은 ORDINAL입니다.
@Enumerated(EnumType.STRING) 어노테이션은 JPA(Java Persistence API)에서 열거형(enum) 필드를 매핑할 때 사용됩니다. </br>
열거형을 데이터베이스에 저장할 방법을 지정하는데, EnumType.STRING은 열거형의 이름을 문자열로 저장하라는 것을 의미합니다.</br>
반면, EnumType.ORDINAL은 열거형의 순서(0부터 시작)를 정수 값으로 저장하라는 것을 의미합니다.

EnumType.ORDINAL을 사용하는 것이 권장되지 않는 주된 이유는, </br>
열거형 상수의 순서가 변경되거나 중간에 새로운 상수가 추가될 경우 데이터베이스에 저장된 값이 더 이상 정확한 열거형 값을 반영하지 못하기 때문입니다.</br>
예를 들어, 다음과 같은 열거형이 있다고 가정해보면

```
public enum Status {
    NEW, IN_PROGRESS, COMPLETED
}
```
이 열거형을 EnumType.ORDINAL로 저장했다고 가정하면, NEW는 0, IN_PROGRESS는 1, COMPLETED는 2로 데이터베이스에 저장됩니다. </br>
만약 나중에 열거형에 새로운 상태를 추가하고자 하여 IN_PROGRESS와 COMPLETED 사이에 PENDING 상태를 추가한다면, PENDING은 2가 되고, 기존에 COMPLETED였던 상태는 3으로 밀려나게 됩니다.</br>
이렇게 되면 데이터베이스에 저장된 기존의 데이터가 더 이상 정확한 상태를 반영하지 못하게 됩니다.</br>

```
public enum Status {
    NEW, IN_PROGRESS, PENDING, COMPLETED // PENDING 추가
}
```
따라서, 열거형의 순서가 변경되거나 중간에 열거형 상수가 추가되어도 영향을 받지 않도록 EnumType.STRING을 사용하여 열거형의 이름을 문자열로 저장하는 것이 권장됩니다. 
이 방법을 사용하면, 데이터베이스에 저장된 값이 항상 명확하고 안정적으로 열거형 값을 반영하게 됩니다.

## 엔티티 설게시 주의점

Setter 사용 x</br>
관계는 지연로딩 설정</br>


## N + 1 문제
Eager로 N + 1 문제 해결 XXX

X To Many 는 기본이 Lazy여서 그냥 두어도 됨 하지만 그렇지 않고 기본이 Eager인 것들은 Lazy로 다 
바꿔야함!

## AllArgsConstructor
Lombok에서 제공하는 기능이며 클래스의 모든 필드를 파라미터로 받는 생성자를 자동으로 생성해주느 기능입니다.
이 기능을 사용함으로 일일이 생성자 작성하는 번거로움을 줄일수 있습니다.
```
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Person {
    private String name;
    private int age;
    private String address;
    
    public static void main(String[] args) {
        Person person = new Person("John", 30, "123 Main St");
        System.out.println(person.getName());  // 출력: John
    }
}
```

## RequiredArgsConstructor
Lombok 에서 제공하는 기능이며 클래스 내에 final로 선언되거나 @NotNull 어노테이션이 붙은 필드에 대해 생성자를 자동으로 생성해주는 어노테이션입니다.
@RequiredArgsConstructor의 주요 목적은 객체의 필수 필드만을 초기화하는데 필요한 생성자를 최소한의 코드로 제공하는 것입니다. 이는 클래스의 불변성을 유지하면서도 필요한 필드에 대한 초기화를 보장하는
간결한 방법을 제공합니다.

```import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Book {
    private final String title;
    private final String author;
    @NonNull private Integer publicationYear;
    private boolean inPrint;

    public static void main(String[] args) {
        // publicationYear는 @NonNull 어노테이션이 붙어 있으므로 생성자에서 초기화가 필요합니다.
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925);
        System.out.println(book.title + ", " + book.author + ", " + book.publicationYear);
    }
}
```