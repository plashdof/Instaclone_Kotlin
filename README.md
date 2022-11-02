# RisingTest 개발일지

### 2022.10.30  내용

---

template 준비 완료

- LoginActivity 구현
    
 <img src ="https://user-images.githubusercontent.com/86242930/199235259-c4b875ab-3d37-41a8-aa27-f9f691ef1761.jpg" width="150" height="300"/>
    

- MainActivity BottomNavigationView 구현

<img src ="https://user-images.githubusercontent.com/86242930/199235290-58584dc8-cebd-413a-938b-2bfd2618c59b.jpg" width="150" height="300"/>

### 2022.10.31  내용

---

인스타 회원가입 단계별 layout & activity 구현완료

**회원가입 화면이동 순서**

- SignupActivity
    - 휴대폰번호 or 이메일 입력
    
    <img src ="https://user-images.githubusercontent.com/86242930/199235326-9a17f02c-d2b8-4f3c-9434-b52f7e60726b.jpg" width="150" height="300"/>
    
- SignuppwActivity
    - 실명 & 비밀번호 입력
    
    <img src ="https://user-images.githubusercontent.com/86242930/199235353-021e4260-725c-4b07-b9ac-b550fb9d650a.jpg" width="150" height="300"/>
    
- SIgnupbirthActivity
    - 생일 입력 (DatePicker 사용)
    
    <img src ="https://user-images.githubusercontent.com/86242930/199235382-13c2ffe0-b96a-45c8-b662-c4c6195fc71a.jpg" width="150" height="300"/>
    
- SignupnickActivity
    - 닉네임 입력
    <img src ="https://user-images.githubusercontent.com/86242930/199235395-73d1baa6-e939-4275-8dbb-86a115b9921c.jpg" width="150" height="300"/>
    

중복된 디자인 적용 및, 버튼 테두리 디자인을 위해 drawable 파일 생성

- shape_loginbtn / shape_loginbtn_active
- shape_signupbtn / shape_signupbtn_active
- shape_et

### 2022.11.01  내용

---

**회원가입 API 테스트 완료**

1.1 signUp API (이슈)

- 데이터베이스 연결실패 이슈 발생
- 서버 팀원에게 전달

<img src ="https://user-images.githubusercontent.com/86242930/199235427-96f90d16-279d-464c-9614-1fdd5043141a.JPG" width="300" height="150"/>

1.2 checkUserKey API

<img src ="https://user-images.githubusercontent.com/86242930/199235437-84ab51df-73cd-4acd-b44c-88c155173b78.JPG" width="300" height="150"/>

1.3 checkUserNickName API 

<img src ="https://user-images.githubusercontent.com/86242930/199235443-31b5c647-c510-4240-aa94-31566fc29d1c.JPG" width="300" height="150"/>

2.1 logIn API (이슈)

- 데이터베이스 연결실패 이슈 발생
- 서버 팀원에게 전달

<img src ="https://user-images.githubusercontent.com/86242930/199235454-3e561dd1-6388-4f5c-98bd-1063d2422f22.JPG" width="300" height="150"/>

template 양식에 맞게 Retrofit2 구현

**logIn API 유효성 검사**

id 를 총 3종류로 입력할 수 있음. (이메일 / 휴대폰번호 / 닉네임)

API에서는 3종류중 하나를 보내면 로그인을 성공시켜주는데, 입력된 값이 이메일 / 휴대폰번호 / 닉네임 중 어떤것인지 확인하는 로직이 필요함

조건문을 활용하여 다음과 같이 구현

```kotlin
// 이메일인지 확인
for(i in 0 until size){
    if(id[i] == '@'){
        emailflag = true
    }
}

// nickname 인지 확인
for(i in 0 until size){
    if(id[i] - '0' < 0 || id[i] - '0' > 9){
        nickflag = true
        phoneflag = false
    }
}
```

### 2022.11.02  내용

---

### 레이아웃

이미지 채도 변경 : ImageButton alpha 속성

이미지 동그라미 : CircleImageView 라이브러리 사용

Toolbar & LinearLayout 양끝 배치 : 가운데에 빈 View 삽입

- **ProfileFragment**

<img src ="https://user-images.githubusercontent.com/86242930/199505010-4827804b-1e7a-4786-8b43-baf9ceb9035b.jpg" width="150" height="300"/>


- **ProfileeditFragment**

<img src ="https://user-images.githubusercontent.com/86242930/199505027-19325255-3572-441b-851d-6dd38162b243.jpg" width="150" height="300"/>

- **ProfileeditTextFragment**

<img src ="https://user-images.githubusercontent.com/86242930/199505060-7e206158-72ea-4eb4-9692-bd286a56159b.jpg" width="150" height="300"/>

### API

4.1API 프로필 정보 불러와서 화면 출력하기 성공.

jwt는 싱글톤 객체를 활용하여 private으로 관리.

getjwt 와 setjwt로 꺼내고 저장할 수 있음.

```kotlin
// 싱글톤 객체

object Jwt {
    private var jwt : String? = ""

    fun setjwt(data : String?){
        jwt = data
    }

    fun getjwt() : String?{
        return jwt
    }
}

// Login 후, MainActivity 에서의 setjwt 구문

Jwt.setjwt(intent.getStringExtra("jwt"))

```

url 이미지는 Glide 라이브러리 사용.

description 과 website 같은경우, 설정을 안해놨으면 해당공간 비어있어야함. visibility로 레이아웃 컨트롤

<img src ="https://user-images.githubusercontent.com/86242930/199505085-248d96cc-45cd-4a4d-813a-5dc12a5b8c80.jpg" width="150" height="300"/>

<img src ="https://user-images.githubusercontent.com/86242930/199505117-051872f3-774a-4fca-9534-62da5b8ee536.JPG" width="400" height="150"/>

### 이슈

- Fragment 간 데이터 전달 이슈. 구상중
