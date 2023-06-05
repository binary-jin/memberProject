package hello.core.singleton;

public class SingletonService {

    //자기 자신을 내부에 Private로 하나 static으로 가지고 있으면 클래스 레벨에 올라가기 때문에 딱 하나만 존재하게 됨
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {

    } //새로 new로 생성하는 것을 막아버리기 위해 private으로 함

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
//자바가 뜨면서 static 영역에 있는 SingletonService를 초기화 하면서 new를 한 번 생성해서 가지고 있고
// instance를 꺼낼 수 있는 곳은 public static SingletonService getInstance() {return instance;} 여기 밖에 없음
//  new로 생성할 수 있는 곳은 아무도 없음
//