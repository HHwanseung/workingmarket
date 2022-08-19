package working.workingmarket.dto.member;

import java.util.Optional;

public class MemberUpdateDto {
    private final Optional<String> name;
    private final Optional<String> nickName;
    private final Optional<Integer> age;

    public MemberUpdateDto(Optional<String> name, Optional<String> nickName, Optional<Integer> age) {
        this.name = name;
        this.nickName = nickName;
        this.age = age;
    }

    public Optional<String> getName() {
        return name;
    }

    public Optional<String> getNickName() {
        return nickName;
    }

    public Optional<Integer> getAge() {
        return age;
    }

}

