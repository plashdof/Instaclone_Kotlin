# RisingTest 개발일지

# 2022.10.30  내용

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

# 2022.11.01  내용

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

# 2022.11.02  내용

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



# 2022.11.03  내용

---

- 프로필 편집시,  데이터 유지 구현 완료

https://user-images.githubusercontent.com/86242930/199724004-fae3bba9-9ed1-4206-9ced-c6440d3f9dbd.mp4

- 프로필 편집 4.4 API 통신테스트 성공

<img src ="https://user-images.githubusercontent.com/86242930/199724024-03f2f7f7-920f-4022-8bc1-8ff92e6a1b3e.JPG" width="400" height="150"/>


- 프로필페이지 PostRecyclerView 스크롤 이슈 해결

- 프로필 사진편집 / 프로필페이지 메뉴버튼 / 프로필페이지 만들기버튼  BottomSheet 구현

Bottom Sheet Dialog 활용위해

`implementation 'com.google.android.material:material:1.5.0-alpha02'` 의존성 추가

Bottom Sheet 에 둥근 모서리 적용

```
<style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
    <item name="bottomSheetDialogTheme">@style/AppBottomSheetDialogTheme</item>
</style>

<style name="AppBottomSheetDialogTheme"
    parent="Theme.Design.Light.BottomSheetDialog">
    <item name="bottomSheetStyle">@style/AppModalStyle</item>
</style>

<style name="AppModalStyle"
    parent="Widget.Design.BottomSheet.Modal">
    <item name="android:background">@drawable/rounded_dialog</item>
</style>
```

→ 프로필 사진편집

<img src ="https://user-images.githubusercontent.com/86242930/199724047-efed6698-b805-4465-aad8-d8fa247d4a1a.jpg" width="150" height="300"/>

→ 프로필페이지 메뉴버튼

<img src ="https://user-images.githubusercontent.com/86242930/199724055-31f1f5cb-f1f9-43e6-bf86-173208a18fce.jpg" width="150" height="300"/>

→ 프로필페이지 만들기버튼

<img src ="https://user-images.githubusercontent.com/86242930/199724064-d536f34b-dbb8-48c6-80f6-28cac975271d.jpg" width="150" height="300"/>


# 2022.11.04 내용

---

### 레이아웃

- **HomeFragment 구현**
    - Story RecyclerView
    - Post RecyclerView
        - ViewPager2
    
    - RecyclerView 안에 ViewPager 를, 어뎁터 두개를 연결하여 구현
    
    ```kotlin
    // PostAdapter.kt
    
    class PostAdapter(private val datas: Array<PostData>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
        inner class ViewHolder(private val viewBinding: RecyclerPostBinding) : RecyclerView.ViewHolder(viewBinding.root){
            fun bind(item:PostData){
                ...
    
                val viewpager = viewBinding.recyclerPostViewpager
                val viewImg = item.imgdata
    
                val adapter = PostViewAdapter(viewImg)
                viewpager.adapter = adapter
    
            }
        }
        
    ...
    }
    
    // PostViewAdapter.kt
    
    class PostViewAdapter(private val datas: Array<String>) : RecyclerView.Adapter<PostViewAdapter.ViewHolder>() {
        inner class ViewHolder(private val viewBinding: ViewpagePostBinding) : RecyclerView.ViewHolder(viewBinding.root){
            fun bind(item:String){
                val img = viewBinding.viewpageImg
    
                Glide.with(itemView)
                    .load(item)
                    .into(img)
            }
        }
        ...
    }
    ```
    
- **viewpager indicator 구현**
    - instadot library 사용 (의존성 추가)
    
    `implementation 'com.github.hrskrs:InstaDotView:1.1'`
    
    - `viewpager.registerOnPageChangeCallback` 을 통해, 페이지에 대칭되는 indicator 디자인 변화
    
    ```kotlin
    
    // Post Adapter.kt   // inner class ViewHolder
    
    val indicator = viewBinding.recyclerPostIndicator
    indicator.noOfPages = viewImg.size
    
    val adapter = PostViewAdapter(viewImg)
    viewpager.adapter = adapter
    
    viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }
    
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            indicator.onPageChange(position)
            Log.d("aaaa","selected position : $position")
        }
    
        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
        }
    })
    ```
    
    - 인스타와 동일하게 보여지도록 레이아웃 세부설정
    
    ```xml
    <com.hrskrs.instadotlib.InstaDotView
            android:id="@+id/recycler_post_indicator"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:layout_constraintTop_toBottomOf="@id/recycler_post_viewpager"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:dot_activeSize="5.5dp"
            app:dot_inactiveSize="4dp"
            app:dot_inactiveColor="#a7a9a8"
            app:dot_mediumSize="4dp"
            app:dot_smallSize="3dp"
            app:dot_margin="3dp"/>
    ```
    

- **세부 Text Layout 구현**

<img src ="https://user-images.githubusercontent.com/86242930/199978725-b55fb6a9-2ce3-4d66-8d8d-2de381bd0b3f.jpg" width="150" height="300"/>


### API

### 이슈

- **스토리 테두리 디자인 이슈**

Story 아이콘 디자인을 위해 gradient 디자인을 활용한 layout-list 를 통하여 중첩된 원 두개로 디자인을 잡았으나,

FrameLayout를 사용하여도 그 위에 사진이 덮어씌기가 안되는 에러 발생.

해결 못하여 gradient 사용 포기. 그냥 shape으로 디자인 잡음

```xml
// 실패한 gradient 테두리 디자인

<layer-list xmlns:android="http://schemas.android.com/apk/res/android" >

    <item>
        <shape android:shape="rectangle" >
            <gradient
                android:angle="45"
                android:centerColor="@android:color/holo_blue_bright"
                android:endColor="@android:color/holo_red_light"
                android:startColor="@android:color/holo_green_light" />

            <corners android:radius="7dp" />
        </shape>
    </item>

    <item
        android:bottom="5dp"
        android:left="5dp"
        android:right="5dp"
        android:top="5dp">

        <shape android:shape="rectangle" >
            <solid android:color="#ffffff" />
            <corners android:radius="7dp" />
        </shape>
    </item>

</layer-list>
```

- **스크롤이슈**

NestedScrollView 내부에 ConstraintLayout을 사용하면, RecyclerView가 안보이는 이슈 발생함!!!

되도록 LinearLayout으로 사용하자!!!

fillviewPort = true




# 2022.11.05 내용

---

### 레이아웃

→ SearchPage 구현

- 스크롤반응 ToolBar
    - `app:contentInsetStart="0dp"`  :  툴바 왼쪽공간 없애줌
    - `app:layout_scrollFlags="scroll|enterAlways"`  :  아래스크롤시 툴바 숨기기

→ SearchrecentPage 구현

- SearchPage 에서 edittext 클릭시, 최근검색어 목록을 보여줌.
- edittext를 버튼으로 활용하기 위해,  아래 속성 추가
    - `android:focusableInTouchMode="false"`
    - `android:clickable="true"`

→ SearchautocompletePage 구현

- edittext에 검색어를 입력하면, 자동완성 fragment로 하단이 교체됨

[검색페이지.mp4](https://user-images.githubusercontent.com/86242930/200122617-5a24d857-be16-468c-9fb7-851fbbc45866.mp4)

→ ProfilePostPage 구현

- ProfilePage 에서 게시물 썸네일 클릭시, 게시물 목록 페이지로 이동
- 내 게시물 또는 태그된 게시물을 RecyclerView 로 보여줌

<img src ="https://user-images.githubusercontent.com/86242930/200122628-65da7e07-53d2-4bd4-a82a-8e8e434d4827.jpg" width="150" height="300"/>

<img src ="https://user-images.githubusercontent.com/86242930/200122631-477e1181-5c8e-49ec-8c60-263fdffc61dc.jpg" width="150" height="300"/>

→ Story 썸네일 클릭시 StoryPage 구현

- Viewpager로 이미지 스와이핑
- 스토리 페이지에서는, statusbar 가 검은색으로 바뀜. 새로운 style resource 구현

```xml
<style name="AppTheme.NoActionBar.black">
    <item name="windowActionBar">false</item>
    <item name="windowNoTitle">true</item>
    <item name="android:windowBackground">@color/insta_black</item>
    <item name="android:statusBarColor">@color/black</item>
</style>
```

- 하단 navigation bar 또한 검은색으로 바뀜. kotlin 에서 변경

```kotlin
window.navigationBarColor = Color.BLACK
```

[story 페이지.mp4](https://user-images.githubusercontent.com/86242930/200122635-c77694ef-eaea-4aef-9dc1-e360fb39f56c.mp4)

### 로직

→ ProfileFragment → ProfilePostFragment 으로의 이동

- 프로필페이지 게시물 썸네일 클릭시,   게시물 페이지로 이동시켜야했다.
- 하지만, 게시물 썸네일이 RecyclerView로 구현되었기 때문에, Fragment 사이의 이동을 Adapter 에서 처리해줘야 했다.  Adapter에서 MainActivity에도 접근이 안되고, FragmentManager 호출도 안되기 때문에,  inner class를 활용하여 페이지 이동함수 자체를 담아서 옮겨 주었다

```kotlin
// ProfileFragment

inner class roomToAdapter{
    fun moveToProfilePost(){
        changeProfile()
    }
}

fun changeProfile(){
    setFragmentResult("fromProfileFragment", bundleOf("bundleKey" to postclick))
    val Activity = activity as MainActivity
    Activity.changeFragment("ProfilePost")
}

var linking = roomToAdapter()
val adapter = ProfileThumbnailAdapter(data,linking)
binding.recyclerProfileThumbnail.layoutManager = GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false)
binding.recyclerProfileThumbnail.adapter = adapter

// ProfileThumbnailAdapter

class ProfileThumbnailAdapter(private val datas : ArrayList<String>, var link : ProfileFragment.roomToAdapter) : RecyclerView.Adapter<ProfileThumbnailAdapter.ViewHolder>(){
    
inner class ViewHolder(private val viewBinding: RecyclerThumbnailBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(item : String){
            ...
            view.setOnClickListener{
                link.moveToProfilePost()
            }
        }
    }
```

→ 키보드올리기 / 내리기 제어  & edittext 포커싱

- Fragment 안에 Fragment를 구현하면서,  Fragment간 이동시 edittext 포커싱 유지 & 키보드 올리기를 하고싶었다
- edittext 포커싱
    - `binding.etSearchtool.requestFocus()`  쏘 간단
- 키보드 컨트롤
    
    ```
    // 컨트롤 변수 선언
    val inputMethodManager =context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    
    // 키보드 올리기
    inputMethodManager.showSoftInput(binding.etSearchtool,0)
    
    // 키보드 내리기
    inputMethodManager.hideSoftInputFromWindow(binding.etSearchAddress.windowToken, 0)
    ```
    

→ edittext 감지하여, 하위 Fragment 교체!!

- EditText가 있는 SearchToolFragment 에서 FrameLayout으로 하단 Fragment를 컨트롤하기 때문에,  EditText에 검색어 입력을 감지하여, autocomplete Fragment로 교체하게끔 구현!

```kotlin
override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    searchtext = binding.etSearchtool.text.toString()
    if(searchtext!!.isNotBlank()){
        parentFragmentManager.beginTransaction()
            .replace(R.id.search_frm, SearchAutocompleteFragment(searchtext))
            .commit()
    }else{
        parentFragmentManager.beginTransaction()
            .replace(R.id.search_frm, SearchRecentsearchFragment())
            .commit()
    }
}
```

### API

### 이슈



