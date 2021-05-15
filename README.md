# Kotlin - springboot
# Web이란?
> 웹은 인터넷에 연결된 컴퓨터를 통해 사람들이 정보를 공유할 수 있는 전 세계적인 정보 공간이다.
웹의 용도는 다양하게 나눌 수 있다.
1. Web site(google, naver, ...)
2. User Interface(Browser)
3. API(ex.Open API)
   이 세 가지 정도가 가장 흔하게 접할 수 있는 웹의 형태다.

### **웹의 기반**
- HTTP
  HTML을 주고 받기 위한 규약
- URI
  리소스 식별자다
- HTML
  XML을 바탕으로 한 범용 문서 포맷. 브라우저는 HTML을 사용자가 알아볼 수 있는 형태로 표현하는 도구다.

### **REST**
1. Client, Server
   클라이언트와 서버가 서로 독립적으로 분리되어야 한다.
2. Stateless
   요청에 대해 클라이언트의 상태가 서버에 저장하지 않는다.
3. Cache
   클라이언트는 서버의 응답을 캐싱할 수 있어야 한다. 클라이언트가 캐시를 통해서 응답을 재사용할 수 있어야 한다. 이를 통해 서버의 부하를 낮출 수 있다.
4. 계층화(Layered System)
   서버와 클라이언트 사이에 방화벽, 게이트웨이, 프록시 등 다계층 형태를 구성할 수 있어야 하며 확장 가능해야 한다.
5. 인터페이스 일관성
   아키텍처를 단순화하고 작은 단위로 분리하여 클라이언트, 서버가 독립적으로 개선될 수 있어야 한다.
6. Code on demand(optional)
   Java applet, Javascript, flash등 특정 기능을 서버가 클라이언트에 코드를 전달하여 실행할 수 있어야 한다.

**인터페이스의 일관성이란?**

인터페이스의 일관성을 잘 지켰다는 것은 REST를 제대로 사용했다는 것이다.
1. 리소스 식별
   웹 기반 REST는 리소스 접근에 URI를 사용한다.
   ex. https://foo.co.kr/user/100
   위 URI에서 user는 리소스, 100은 식별자다.
2. 메세지를 통한 리소스 조작
   Web은 다양한 방식으로 데이터를 전송할 수 있다. HTML, XML, JSON, TEXT등. 리소스의 타입을 알리기 위해 HTTP header에 Content-Type을 추가하여 어떤 리소스인지 지정해야 한다.
3. 자기 서술적 메세지
   요청하는 데이터가 어떻게 처리되어야 하는 지 충분한 데이터를 포함해야 한다. HTTP기반 REST에서는 HTTP Method와 Header 데이터를 통해 이를 표현할 수 있다.
4. 애플리케이션 상태에 대한 엔진으로서 하이퍼미디어
   REST API를 개발할 때 단순히 클라이언트 요청에 대한 데이터만 돌려주는 것이 아니다. 리소스에 대한 link 데이터까지 같이 포함되어야 한다.(HATEOAS)

이런 조건을 잘 갖춘 경우 Restful하다고 한다.

### **URI**
- URI는 인터넷에서 특정 자원을 나타내는 주소 값이다. 해당 값은 유일해야 한다.
- URL은 인터넷 상에서 특정 자원을 나타낸다.
  foo.co.kr/sample.pdf라는 URI가 있다면 여기서 리소스를 나타내는 sample.pdf가 URL이다. URL은 URI의 하위 개념이다.

### **URI 설계 원칙(RFC-3986)**
- 슬래시 구분자(/)는 계층 관계를 나타낸다.
- 마지막 문자로 슬래시(/)를 포함하지 않는다
- 하이픈(-)은 가독성을 높일 때 사용한다.
- 언더바(_)는 사용하지 않는다.
- URI는 소문자가 적합하다.
- 파일 확장자는 URI에 포함하지 않는다.(.jsp 같은 것)
- 프로그래밍 언어에 의존적인 확장자를 사용하지 않는다.(.do)
- 구현에 의존적인 경로를 사용하지 않는다.(http://foo.co.kr/servlet <-)
- SESSION ID를 포함하지 않는다
- 프로그래밍 언어의 method명을 사용하지 않는다.
- 명사에 단수형보다 복수형을 사용해야 한다. 컬렉션에 대한 표현은 복수로 사용
- 컨트롤러 이름으로 동사나 동사구를 사용한다.
- 경로 부분 중 변하는 부분은 유일한 값으로 대체한다.
  https://foo.co.kr/vehicles/suv/{car-id}/users/{user-id}/release
  이처럼 URI에서 변하는 부분에 대해서 유연하게 반응할 수 있도록 설계해야 한다
- CRUD기능을 나타내는 것은 uri에 사용하지 않는다.
  잘못된 설계 GET : foo.co.kr/vehicles/q7/delete/{car-id}
  올바른 설계 DELETE: foo.co.kr/vehicles/q7/{car-id}
- URI Query Parameter 디자인
    1. URI 쿼리 부분으로 컬렉션 결과에 대해 필터링할 수 있다. 만약 모든 종류의 suv차량 정보를 제공하는 API가 있다고 가정하자. 그 API의 주소는 'foo.co.kr/vehicles/suv'다. 이 중에서 'Q7'이라는 모델만 찾고 싶다면 쿼리 파라미터를 사용해야 한다. 'foo.co.kr/vehicles/suv?model=q7' 이런 식으로 디자인해야 한다
    2. URI쿼리는 컬렉션의 결과를 페이지로 구분하여 나타낼 때 사용한다.
       ex) foo.co.kr/vehicles/suv?page=1&size=10&sort=desc
- API에 있어서 서브 도메인은 일관성 있게 사용해야 한다.
  이를테면 foo.co.kr이라는 도메인을 사용하는데, api를 제공하는 도메인을 추가해야 한다고 하자.
  이럴 때는 api.foo.co.kr처럼 일관성있게 도메인을 설계해야 한다.
- 클라이언트 - 개발자 서브 도메인도 일관성 있게 설계해야 한다.

### **HTTP**
- HTTP는 RFC2616에서 규정한 WEB에서 데이터를 주고 받을 때 사용하는 약속이다.
- 이름은 하이퍼텍스트 전송용 프로토콜이지만, 실제로 HTML, XML, JSON, Image, Voice, Video, Javascript, PDF 등 컴퓨터에서 다룰 수 있는 것은 모두 전송할 수 있다.
- HTTP는 TCP를 기반으로 한 REST의 특징을 모두 구현하고 있는 Web기반 프로토콜이다.

HTTP는 메세지를 주고(Request) 받는(Response) 형태의 통신 방식이다. 전통적인 방식에서 HTTP는 Request를 보내고 Response를 기다린다.

HTTP Method
HTTP는 아홉 가지의 Method를 가지고 있다.

- GET(리소스 취득)

  멱등성을 보장하므로 (같은 결과를 반환한다는 점에서)안정적이다.
  PathVariable과 Query parameter를 사용할 수 있다.
  Body를 가지지 않는다.
- POST(리소스 생성, 추가)

  멱등성을 보장하지 않으므로 안정적이지 않다. 요청할 때마다 새로운 리소스가 생성된다.
  PathVariable을 사용할 수 있다. Query parameter는 사용할 수 있으나 사용하지 않는다.
  Body를 가질 수 있다.
- PUT(리소스 갱신, 생성)

  멱등성을 보장한다. 왜? 이름이 hoon인데, 첫 문자를 대문자 H로 바꾼다고 가정하자. 처음 PUT을 호출하면 수정이 된 Hoon을 반환한다. 여러 번 호출해도 결과는 같다. 만약 Hoon이 없어서 Create되었을 경우에도 마찬가지다.
  하지만 리소스의 데이터를 변경하기 때문에 안정성은 없다. PathVariable을 사용할 수 있다. 쿼리 파라미터는 사용할 수 있으나 사용하지 않는다.
  Body를 가질 수 있다.
- DELETE(리소스 삭제)

  멱등성을 보장한다. DELETE호출 시 데이터가 있으면 삭제 되어 영영 없고, 없으면 원래 없던 것이니 멱등하다.
  리소스를 지우기 때문에 안정성은 없다.
  PathVariable, query parameter사용 가능하다.
  Body는 사용할 수 없다.
- HEAD
- OPTIONS
- TRACE
- CONNECT
- PATCH

**HTTP Satus code**
- 1xx
  처리가 진행되고 있는 상태.
- 2xx
  성공
- 3xx
  Redirect
- 4xx
  클라이언트의 요청이 잘못된 경우
- 5xx
  Server error

