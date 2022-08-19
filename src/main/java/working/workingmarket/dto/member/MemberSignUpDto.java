package working.workingmarket.dto.member;


import working.workingmarket.entity.member.Member;

public class MemberSignUpDto{

    private final String name;
    private final String username;
    private final String password;
    private final String nickName;
    private final Integer age;

    public MemberSignUpDto(String name, String username, String password, String nickName, Integer age) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.nickName = nickName;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNickName() {
        return nickName;
    }

    public Integer getAge() {
        return age;
    }

    public Member toEntity() {
        return Member.builder().username(username).password(password).name(name).nickName(nickName).age(age).build();
    }

}