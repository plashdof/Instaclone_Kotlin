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




# 2022.11.06 내용

---

### 레이아웃

→ StoryPage 디테일 적용

- 중첩 ViewPager2가 가능해야했음
- Util 폴더에 NestedScrollableHost 추가.
- 스크롤을 하는 자식 ViewPager2 XML에 NestedScrollableHost 를 감싸줌

→ 중첩 ViewPager2 배제!! : 그냥 image와 time data만, 바꿔주는 View로 변경

- 써드파티 라이브러리 `implementation 'com.github.shts:StoriesProgressView:3.0.0'` 사용
- insta story progress bar 구현
- Fragment에서 벗어나, StoryToolActivity를 별도로 생성

→ 이슈해결 :  HorizontalScrollView 내부에서 RecyclerView와 다른View 함꼐 스크롤시키기

- HorizontalScrollView 내부에는 RelativeLayout으로 작성.

https://user-images.githubusercontent.com/86242930/200178772-fa835f58-e3d8-4bae-89e2-5736080bb6f7.mp4

→ ReelsPage 레이아웃 구현

- Vertical Viewpager2 로 구현

→ ShoppingPage 레이아웃 구현

- SearchPage와 유사한 단계로 구현.

https://user-images.githubusercontent.com/86242930/200178779-0ff243dc-f991-4bd4-a236-b1f8f5480557.mp4

### 로직

→ 스토리페이지,  상태바&네비게이션 바 색상 변경 

- 네비게이션 바는 수동으로 설정
- *`window*.*navigationBarColor* = Color.*BLACK*`
- 스타일 적용

```
<style name="AppTheme.NoActionBar.black">
    <item name="windowActionBar">false</item>
    <item name="windowNoTitle">true</item>
    <item name="android:windowBackground">@color/insta_black</item>
    <item name="android:statusBarColor">@color/black</item>
</style>
```

→ 스토리페이지 클릭시 이미지 변경 및 progressbar 상태 변경 구현

```kotlin
// 스토리 progressbar
progressbar = viewBinding.storyProgressbar
progressbar.setStoriesCount(item.imgData.size)
progressbar.setStoryDuration(4000L)
progressbar.setStoriesListener(this@ViewHolder)
progressbar.startStories()

leftside.setOnClickListener {

    if(currentindex != 0){
        --currentindex
        Glide.with(itemView)
            .load(item.imgData[currentindex].img)
            .into(storyimg)
        time.text = item.imgData[currentindex].time
        progressbar.reverse()

    }

}

rightside.setOnClickListener {

    clicked = true

    if(currentindex != maxindex){
        ++currentindex
        Glide.with(itemView)
            .load(item.imgData[currentindex].img)
            .into(storyimg)
        time.text = item.imgData[currentindex].time
        progressbar.skip()
    }

}
```

- 화면 좌우에 빈 View를 덮어씌운뒤,   왼쪽화면 클릭시 뒤로가기 & 오른쪽화면 클릭시 앞으로가기 구현

### API

→ 3.3 API 통신테스트 성공

- 홈화면 게시물 리스트 GET API


<img src ="https://user-images.githubusercontent.com/86242930/200178781-da6d03ec-a7ae-483c-9639-53b688e433bd.JPG" width="400" height="150"/>

https://user-images.githubusercontent.com/86242930/200178784-6c821ae0-6c2e-4b46-be93-908070824caf.mp4

### 이슈

→ Retrofit 중복 콜 문제

- OkHttpClient 의 빌딩과정의 문제인걸 확인
- Build시 `.retryOnConnectionFailure(false)` 추가

→ 갤러리 연동 실패

- 스토리/게시물 작성 & 프로필사진 변경을 위해,  이미지 연동 커스텀 페이지 제작 시도
- 실패.. ContentProvider를 활용하여 재도전 해볼 예정








# 2022.11.07 내용

---

### 레이아웃

→ 원형 ProgressBar 색상변경

- indeterminateTint = “” 로 설정

→ 댓글페이지 구현

<img src ="https://user-images.githubusercontent.com/86242930/200373210-6770316d-9d2b-46dc-b1d9-903a6e502a3a.jpg" width="150" height="300"/>

→ 좋아요페이지 구현

<img src ="https://user-images.githubusercontent.com/86242930/200373243-3312e776-c683-413e-8882-b3bcf7052d62.jpg" width="150" height="300"/>

→ 타인프로필 페이지 구현

<img src ="https://user-images.githubusercontent.com/86242930/200373257-e265b19a-d04c-4165-b6fd-7f23d0ab44ca.jpg" width="150" height="300"/>

### 로직

→ Home 게시물 페이징 구현

```kotlin
private var dataCount = 0
private var nextpage = false
private var page  = 0
private val datas = arrayListOf<PostdetialData>()

// 최하단 스크롤 감지시, getMoreData 실행
binding.homeScroll.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

    if(!v.canScrollVertically(1)){
        Log.d("aaaaa","lastScroll")

        getMoreData()
    }
}

// 전역변수로 설정된 page 수를 증가시켜, 다음페이지 데이터롤 서버에서 GET한다

fun getMoreData(){
  nextpage = true
  page++
  
  // page수를 증가시켜서 서버 요청
  HomeService(this).tryGetHomePostData(Jwt.getjwt(),page.toString())
}

// 통신성공시, 전역으로 설정된 data 변수에 추가데이터를 이어붙인뒤,  해당 데이터로 recyclerview 호출한다

override fun onGetHomePostDataSuccess(response: PostData) {
    if(response.isSuccess){
        dataCount = response.result.postList.size
        page = response.result.page
        for(i in response.result.postList){
            datas.add(i)
        }

        recyclerPost(datas)
    }
}

// recyclerview adapter 연결시에는, nextpage boolean 변수로,  처음페이지일떄와 구분지어서 호출해준다
// .notifyItemInserted 와 notifyDataSetChanged 로  아이템이 추가됨과 data가 변경됨을 알려준다

private fun recyclerPost(datas : ArrayList<PostdetialData>){

    if(nextpage){
        binding.recyclerHomeBody.adapter?.notifyItemInserted(datas.size)
        binding.recyclerHomeBody.adapter?.notifyDataSetChanged()
    }

    val linking = getcontext()

    val adapter = PostAdapter(datas, linking)
    binding.recyclerHomeBody.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    binding.recyclerHomeBody.adapter = adapter

}
```

### API

→ 3.11 3.12 API (좋아요/좋아요취소)

<img src ="https://user-images.githubusercontent.com/86242930/200373285-ce6533c6-28b7-4011-a00f-6d8d50e4ffa6.JPG" width="400" height="150"/>

→ 3.13 API (게시글 좋아요 리스트 조회 API)

<img src ="https://user-images.githubusercontent.com/86242930/200373293-ca96c7f2-207d-45c7-b586-02329d0621f8.JPG" width="400" height="150"/>

→ 4.2 API (타인프로필 조회 API)

<img src ="https://user-images.githubusercontent.com/86242930/200373303-ed9fa382-a7a2-46a0-ac46-3f4ab8c89ade.JPG" width="400" height="150"/>

→ 6.1 6.2 API (팔로우/팔로우취소)

<img src ="https://user-images.githubusercontent.com/86242930/200373313-3e4ebafd-2769-457f-833e-411003ee3bd9.JPG" width="400" height="150"/>

<img src ="https://user-images.githubusercontent.com/86242930/200373323-8b2621fa-dbe9-4893-8036-6fe044467585.JPG" width="400" height="150"/>




# 2022.11.08 내용

---

## 레이아웃

→ StatusBar, NavBar 색상, 글자 동적 변경

- 

```kotlin
// reels 제외 화면

binding.mainBtmNav.itemBackground= Color.rgb(254,254,254).toDrawable()
binding.mainBtmNav.setBackgroundColor(Color.rgb(254,254,254))

val controller = ViewCompat.getWindowInsetsController(window.decorView)
controller?.isAppearanceLightStatusBars= true   //  StatusBar 글자 회색으로
controller?.isAppearanceLightNavigationBars= true   // NavBar 글자 회색으로

window.statusBarColor= Color.rgb(254,254,254)
window.navigationBarColor= Color.rgb(254,254,254)

// reels 화면

binding.mainBtmNav.itemBackground = Color.BLACK.toDrawable()
binding.mainBtmNav.setBackgroundColor(Color.BLACK)

val controller = ViewCompat.getWindowInsetsController(window.decorView)
controller?.isAppearanceLightStatusBars = false  //  StatusBar 글자 흰색으로
controller?.isAppearanceLightNavigationBars = false  // NavBar 글자 흰색으로

window.navigationBarColor = Color.BLACK
window.statusBarColor = Color.BLACK
```

```kotlin

// 투명상태바. (전체화면) 레이아웃 위로 올라감
WindowCompat.setDecorFitsSystemWindows(window, false) 

// 불투명상태바
WindowCompat.setDecorFitsSystemWindows(window, true) 
```

→   스토리 작성 레이아웃

- 카메라로 사진을찍으면, 스토리 작성 레이아웃으로 이동한다.
- 이곳에서 최종 스토리에 올릴지 결정한다!

https://user-images.githubusercontent.com/86242930/200609674-d962ecf4-912c-4de4-964a-16e993960be6.mp4

https://user-images.githubusercontent.com/86242930/200609683-3ffd5ba3-28d4-480a-b749-3b93551fbd9b.mp4

## 로직

→ 카메라접근 & 찍은사진 불러와서 출력

- deprecated 된 메소드가 너무 많아서 엄청 애먹었다….
- 결과적으로, Camera 로 찍은 사진을 넘겨받기 위해서,  registerForAcrtivity Result를 사용하였다.

```kotlin
private fun openCamera() {
    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    state = false
    Log.d("aaaaaa","openCamera")

    createImageUri(newFileName(), "image/jpg")?.let { uri ->
        realUri = uri
        // MediaStore.EXTRA_OUTPUT을 Key로 하여 Uri를 넘겨주면
        // 일반적인 Camera App은 이를 받아 내가 지정한 경로에 사진을 찍어서 저장시킨다.
        intent.putExtra(MediaStore.EXTRA_OUTPUT, realUri)
        intent.also{
            childForResult.launch(it)
        }
    }
}

@SuppressLint("SimpleDateFormat")
private fun newFileName(): String {
    val sdf = SimpleDateFormat("yyyyMMdd_HHmmss")
    val filename = sdf.format(System.currentTimeMillis())
    return "$filename.jpg"
}

private fun createImageUri(filename: String, mimeType: String): Uri? {
    val Activity = activity as MainActivity
    var values = ContentValues()
    values.put(MediaStore.Images.Media.DISPLAY_NAME, filename)
    values.put(MediaStore.Images.Media.MIME_TYPE, mimeType)
    return Activity.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
}

private val childForResult =
    registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val Activity = activity as MainActivity
        Log.d("aaaaaa","$realUri")
        setFragmentResult("fromCamera", bundleOf("bundleKey" to realUri))
        Activity.changeFragment("Makestory")
    }
```

→ 스토리데이터 parsing

- 서버에서 넘겨받은  스토리생성후 지난 시간값 createAt를 이용하여, “몇시간전” 출력값을 출력하였다

```kotlin
// 시간 데이터 parsing
if(item.storyDataList[0].createdAt < 60){
    time.text = "${(item.storyDataList[0].createdAt)}분 전"
}else{
    time.text = "${(item.storyDataList[0].createdAt)/60}시간 전"
}
```

## API

→ 5.1 API (스토리생성)

<img src ="https://user-images.githubusercontent.com/86242930/200609693-792ab5f5-bafe-4f92-97f6-b767cf0c8287.JPG" width="400" height="150"/>

→ 5.2 API (홈 스토리 데이터리스트)

<img src ="https://user-images.githubusercontent.com/86242930/200609702-4ca6c738-9c27-4af8-b5a3-c356986cd8ba.JPG" width="400" height="150"/>

→ 5.3 API (target 스토리 데이터)

<img src ="https://user-images.githubusercontent.com/86242930/200609707-7b63b7ec-b3a6-4bc6-80d0-361ac9de33c8.JPG" width="400" height="150"/>


# 2022.11.09 내용

---

## API

→ 3.4 API (댓글 위 게시물 본문)

→ 3.10 API (댓글 리스트)

# 2022.11.10 내용

---

## 구현완성 파트

### 1. 레이아웃

**→ 로그인**

**→ 회원가입**

- 휴대폰/이메일 선택 페이지
- 생일 선택 페이지
- 닉네임 선택 페이지

**→ 홈**

- 스토리썸네일 (RecyclerView)
- 스토리리스트 (ViewPager2)
- 게시물리스트 (RecyclerView / 무한스크롤paging)
- 좋아요리스트 (RecyclerView)
- 댓글리스트 (RecyclerView)

**→ 검색**

- 검색 썸네일  (RecyclerView / 무한스크롤paging)
- 최근검색리스트  (RecyclerView)
- 자동검색리스트  (RecyclerView)

**→ 릴스**

- 릴스 기본 페이지  (ViewPager2)

**→ 쇼핑**

- 쇼핑썸네일  (RecyclerView)
- 쇼핑 자동검색  (RecyclerView)

**→ 프로필**

- 내 프로필
- 타인 프로필
- 프로필 편집
- 프로필 하이라이트 리스트 (RecyclerView)
- 프로필 게시물 썸네일  (RecyclerView)
- 프로필 태그됨 썸네일  (RecyclerView)
- 프로필 게시물 리스트  (RecyclerView)
- 프로필 태그됨 리스트  (RecyclerView)

### 2. 기능& 로직

→ 무한스크롤 (페이징)

→ 카메라 찍은 사진 가져오기 

→ 싱글톤 객체로 페이지간 데이터이동 간소화

→ MVP 템플렛에 맞춘 Retrofit2 활용

→ 조건문/반복문을 활용한 데이터 parsing

→ 스토리작성 & 게시물작성

→ 프로필 편집

→ 게시물 좋아요클릭 / 츼소

→ 사용자 팔로우 / 팔로우 취소

→ 댓글작성 /

→ 대댓글 작성 

→ 댓글 좋아요 / 좋아요 취소

### 3. API

**→ 회원가입**

- 1.1 : 회원가입
- 1.2 : 이메일/전화번호 유효성 검사
- 1.3 : 닉네임 유효성 검사

**→ 로그인**

- 2.1 : 로그인

**→ 게시물**

- 3.1 : 게시글 생성
- 3.3 : 홈화면 게시물 조회
- 3.4 : 댓글창 위 게시글 본문 조회
- 3.5 : 게시글 대댓글 작성
- 3.8 : 게시글 댓글 작성
- 3.10 : 게시글 댓글 전체 조회
- 3.11 : 게시글 좋아요
- 3.12 : 게시글 좋아요 취소
- 3.13 : 게시글 좋아요 누른 사람 목록
- 3.14 :  유저 프로필 화면 게시글 썸네일 목록
- 3.15 : 썸네일 클릭시 리스트 조회
- 3.16 : 유저 프로필 화면 태그된 게시글 썸네일 목록
- 3.17 : 태그된 게시물 클릭시 리스트 조회
- 3.19 : 댓글 좋아요
- 3.20 : 댓글 좋아요 취소

**→ 프로필**

- 4.1 : 자기자신 프로필 조회
- 4.2 : 타인 프로필 조회
- 4.4 : 프로필 편집

**→ 스토리**

- 5.1 : 스토리 생성
- 5.2 : 스토리 리스트 조회
- 5.3 : 단일 스토리 조회

**→ 팔로우**

- 6.1 : 팔로우
- 6.2 : 팔로우 해제

**→ 검색**

- 7.1 : 검색창 섬네일 리스트 조회
- 7.2 : 검색어 자동완성

## 마무리글

많은걸 얻고 꺠닫게된 2주였다. 직접 부딪혀보며 시간제한이 걸린 프로젝트를 협업으로 진행을 해보니, 초반에 어떤식으로 설계하고 계획해야 시간이 절약되는지 여실히 느낄 수 있었다.  

2주 끝자락쯤 되니 수많은 변수와 파일들로 인해  내가 직접짠 코드임에도 알아볼수 없는 현상까지 이르게 되었다. 변수네이밍과 폴더,파일정리의 중요성을 느꼈다.

또한, 한가지 기능을 개발하다보면, 시간투자를 너무 많이하게되어 다음기능에 지장이 생기는 순간도 많았다. 중요도 체크와 시간배분의 중요성을 느꼈다.

팀원과의 소통은 큰 문제가 없었던것 같다. 다만 프로젝트 중간에 되서야 프론트의 API 우선순위를 서버쪽에 전달하였는데,  그 시점이 조금 빨랐으면 좋았을걸 이라는 아쉬움이 들었다

다른 앱과의 연동(카메라, 갤러리) 가 가장 어려운 부분이었다. 프로젝트가 끝나고 content provider 를 더 심도있게 파고들어보고 싶다.  

아쉬운만큼 얻어가는것도 많았던 라이징테스트.

