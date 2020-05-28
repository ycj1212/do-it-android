# 📕 Do it! Android App Programming

## 레이아웃

레이아웃 이름        | 설명
-|-
Constraint Layout   | 제약조건 사용
LinearLayout        | 한 쪽 방향으로 뷰 추가
RelativeLayout      | 부모 컨테이너나 다른 뷰와의 상대적 위치로 화면을 구성, Constraint를 사용하게 되면서 권장하지 않음
FrameLayout         | 여러 개의 뷰를 중첩하여 가장 상위의 뷰만 보여줌
TableLayout         | 격자 모양의 배열을 사용

### - LinearLayout

- orientation: 필수 속성

#### 뷰 정렬하기

속성            | 설명
-|-
layout_gravity  | 부모 컨테이너의 여유 공간에 뷰가 채워지지 않아 여유 공간이 생겼을 때 여유 공간 안에서 뷰를 정렬
gravity         | 뷰 안에 표시하는 내용물을 정렬

- baselineAligned: 텍스트 우선 정렬

- layout_weight

- 정확한 비율로 설정할 경우 width나 weight를 0으로 설정해야함

### - RelativeLayout

속성            | 설명
-|-
layout_above        | 지정한 뷰의 위쪽에 배치
layout_below        | 지정한 뷰의 아래쪽에 배치
layout_toLeftOf     | 지정한 뷰의 왼쪽에 배치
layout_toRightOf    | 지정한 뷰의 오른쪽에 배치
layout_alignTop     | 지정한 뷰의 위쪽과 맞춤
layout_alignBottom  | 지정한 뷰의 아래쪽과 맞춤
layout_alignLeft    | 지정한 뷰의 왼쪽과 맞춤
layout_alignRight   | 지정한 뷰의 오른쪽과 맞춤

### - TableLayout

- 최상위 레이아웃이 될 수 없음
- stretchColumns
- shrinkColumns
- layout_column
- layout_span

### - FrameLayout

- 중첩(Overlay)  
- 가시성(Visibility)  

### - ScrollView

- HorizontalScrollView  
- VerticalScrollView

## 기본 위젯

### TextView

- #### 다국어 지원 방법

리소스에 공통으로 적용되는 병렬 리소스 로딩(Parallel Resource Loading) 방식을 사용

```
/app/res
    values-en/  <-- 영어 지원
        strings.xml
    values-ko/  <-- 한글 지원
        strings.xml
```

- #### textColor

`#AARRGGBB` - 각각 Alpha, Red, Green, Blue를 의미  
Alpha - 불투명(FF), 투명(00)  

- #### textSize

textSize는 sp단위 사용 권장

### Button

- #### RadioButton

RadioGroup 안에 정의

- #### CheckBox

### EditText

- #### inputType

입력하는 문자의 유형을 지정

- #### hint

어떤 내용을 입력해야 할지 알림  
textColorHint로 색상 변경 가능

- #### selectAllOnFocus

true이면 포커스를 받을 때 문자열 전체가 선택됨

- #### cursorVisible

커서 가시성

- #### autoLink

하이퍼링크

- #### lineSpacingMultiplier, lineSpacingExtra

줄 간격 조정

- #### capitalize

대소문자 조절(characters, words, sentences)

- #### ellipsize

생략 부분 설정(start, middle, end)

- #### addTextChangedListener()

텍스트가 변경될 때마다 발생하는 이벤트 처리


### ImageView

- #### android:src 또는 app:srcCompat

res/drawable 에 있는 이미지 파일을 불러옴

- #### maxWidth, maxHeight

이미지의 최대 크기 지정

- #### tint

이미지뷰에 보이는 이미지의 색상 설정

- #### scaleType

이미지뷰의 크기에 맞게 원본 이미지의 크기를 원하는 형태로 조절하여 보여줄 때 사용  
fitXY, centerCrop, centerInside 등

- #### drawable 폴더

```
// 초고해상도
res/drawable-xhdpi
res/drawable-xxhdpi
res/drawable-xxxhdpi

// 고해상도
res/drawable-hdpi

// 중간 해상도
res/drawable-mdpi

// 저해상도
res/drawable-ldpi
```

## Drawable 

드로어블 | 설명
-|-
BitmapDrawable | 이미지 파일을 보여줄 때 사용
StateListDrawable | 상태별로 다른 비트맵 그래픽을 참조
TransitionDrawable | 두 개의 드로어블을 서로 전환할 수 있음
ShapeDrawable | 색상과 그라데이션을 포함하여 도형 모양을 정의할 수 있음
InsetDrawable | 지정된 거리만큼 다른 드로어블을 들어서 보여줄 수 있음
ClipDrawable | 레벨 값을 기준으로 다른 드로어블을 클리핑할 수 있음
ScaleDrawable | 레벨 값을 기준으로 다른 드러어블의 크기를 변경할 수 있음

## Event

속성 | 설명
-|-
터치 이벤트 | 화면을  손가락으로 누를 때 발생하는 이벤트
키 이벤트 | 키패드나 하드웨어 버튼을 누를 때 발생하는 이벤트
제스처 이벤트 | 터치 이벤트 중에서 스크롤과 같이 일정 패턴으로 구분되는 이벤트
포커스 | 뷰마다 순서대로 주어지는 포커스
화면 방향 변경 | 화면의 방향이 가로와 세로로 바뀜에 따라 발생하는 이벤트

### Touch Event

### Gesture Event

### Key Event

### 단말 방향 전환

## Toast, Snackbar, Dialog

### Toast

- this
- getApplicationContext()
- setGravity(): 토스트의 위치 설정
- setMargin(): 토스트의 여백 설정

### Snackbar

### Dialog

## ProgressBar

- 막대 모양
- 원 모양

## LayoutInflation

## 화면 간 전환하기

### 안드로이드 앱의 기본 구성 요소

- 액티비티
- 서비스
- 브로드캐스트
- 내용 제공자

안드로이드 시스템이 이 요소에 대한 정보를 요구

1. startActivityForResult

2. setResult

3. onActivityResult


startActivity()  
startService()  
broadcastIntent()  

인텐트의 기본 구성 요소 액션과 데이터

속성 | 설명
-|-
ACTION_DIAL tel:01077881234 | ㅈ
ACTION_VIEW tel:01077881234 | ㅈ
ACTION_EDIT content://contacts/people/2 | ㅈ
ACTION_VIEW content://contacts/people | ㅈ

MIME 타입?

명시적 인텐트  
: 인텐트에 클래스 객체나 컴포넌트 이름을 지정하여 호출할 대상을 확실히 알 수 있는 경우

암시적 인텐트  
: 액션과 데이터를 지정하긴 했지만 호출할 대상이 달라질 수 있는 경우

암시적 인텐트 속성

- 범주(Category)
- 타입(Type)
- 컴포넌트(Component)
- 부가 데이터(Extras)

### 플래그

인텐트로 인해 동일한 액티비티를 여러 번 사용하게 되는 경우를 방지

FLAG_ACTIVITY_SINGLE_TOP  
FLAG_ACTIVITY_NO_HISTORY  
FLAG_ACTIVITY_CLEAR_TOP  

#### 인텐트를 처리 방법

- 액티비티가 새로 만들어지는 경우  
onCreate() {
    getIntent()
}  

- 액티비티가 재사용되는 경우  
onNewIntent(Intent intent)

### 부가데이터

인텐트 안에는 번들 객체가 들어있음  
번들 객체는 해시테이블과 유사

putExtra()  
getStringExtra()  

전달하고 싶은 데이터가 객체 자료형인 경우 객체 자체를 전달할 수 없음  
객체 데이터는 바이트 배열로 변환하거나 Serializable 인터페이스를 구현하는 객체를 만들어 직렬화한 다음 전달해야함  
안드로이드는 Serializable 인터페이스와 유사한 Parcelable 인터페이스 권장  
직렬화했을 때 크기가 더 작기 때문


public abstract int describeContents()  
직렬화하려는 객체의 유형을 구분할 때 사용  

public abstract void writeToParcel(Parcel dest, int flags)  
객체가 가지고 있는 데이터를 Parcel 객체로 만듦

### 태스크

태스크는 앱이 어떻게 동작할 지 결정하는데 사용된다.  
즉, 태스크를 이용하면 프로세스처럼 독립적인 실행 단위와 상관없이 어떤 화면들이 같이 동작해야 하는지 흐름을 관리할 수 있다.  
프로세스는 독립적으로 실행됨  

```xml
<activity android:name=".MainActivity"
        android:launchMode="singleTop">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```

### 액티비티의 수명주기

#### 액티비티의 대표적인 상태 정보

상태 | 설명
-|-
실행(Running) | 화면상에 액티비티가 보이면서 실행되어 있는 상태. 액티비티 스택의 최상위에 있으며 포커스를 가지고 있음
일시정지(Paused) | 사용자에게 보이지만 다른 액티비티가 위에 있어 포커스를 받지 못하는 상태. 대화상자가 위에 있어 일부가 가려진 경우에 해당함.
중지(Stopped) | 다른 액티비티에 의해 완전히 가려저 보이지 않는 상태

액티비티의 상태 정보가 변화하는 것을 액티비티의 수명주기(Life Cycle) 또는 생명주기라고 함.

- 액티비티 처음 실행 시  
onCreate() -> onStart() -> onResume()

- 액티비티 전환 시(A -> B)  
A: onPause() -> onStop() -> (finish()) onDestroy()  
B: onRestart() -> onStart() -> onResume()

onSaveInstanceState()  
onRestoreInstanceState()  

### 프래그먼트

- 화면의 일부분 구성

사용 목적
- 분할된 화면들을 독립적으로 구성
- 분할된 화면들의 상태를 관리

프래그먼트는 항상 액티비티 위에 올라가있어야 함

[Fragment]

`public final Activity getActivity()`  
: 이 프래그먼트를 포함하는 액티비티 반환

`public final FragmentManager getFragmentManager()`  
: 이 프래그먼트를 포함하는 액티비티에서 프래그먼트 객체들과 의사소통하는 프래그먼트 매니저 반환

`public final Fragment getParentFragment()`  
: 이 프래그먼트를 포함하는 부모가 프래그먼트일 경우 리턴. 액티비티이면 null을 반환

`public final int getId()`  
: 이 프래그먼트의 ID를 반환

[FragmentManager]

`public abstract FragmentTransaction beginTransaction()`  
: 프래그먼트를 변경하기 위한 트랜잭션을 시작

`public abstract Fragment findFragmentById(int id)`

`public abstract Fragment findFragmentByTag(String tag)`

`public abstract boolean executePendingTransactions()`  
: 트랜잭션은 commit() 메소드를 호출하면 실행되지만 비동기 방식으로 실행되므로 즉시 실행하고 싶다면 이 메소드를 추가로 호출해야함.

`getSupprotFragmentManager()`: 예전 버전 호환  
`getFragmentManager()`

다른 프래그먼트로 변경 시 트랜잭션 사용

`supportFragmentManager.beginTransaction().replace(/* */).commit()`

#### 프래그먼트 수명주기

onAttach() - 프래그먼트가 액티비티와 연결될 때  
onCreate() - 프래그먼트가 초기화될 때  
onCreateView() - 프래그먼트와 관련되는 뷰 계층을 만들어서 반환  
onActivityCreated() - 프래그먼트와 연결된 액티비티가 onCreate() 메서드의 작업을 완료했을 때  
onStart()  
onResume()  
onPause()  
onStop()  
onDestroyView() - 프래그먼트와 관련된 뷰 리소스를 해제할 수 있도록 호출됨  
onDestroy() - 프래그먼트의 상태를 마지막으로 정리할 수 있도록 호출됨  
onDetach() - 프래그먼트가 액티비티와 연결을 끊기 바로 전에 호출됨

- 액티비티에 프래그먼트 추가  
onAttach() -> onCreate() -> onCreateView() -> onActivityCreated() -> onStart() -> onResume()

- 액티비티에서 프래그먼트 제거  
onPause() -> onStop() -> onDestroyView() -> onDestroy() -> onDetach()

- Back stack에서 복구되는 경우  
onDestroyView() -> onCreateView()

#### 중요!

- 프래그먼트는 액티비티 위에 올라가지 않고서는 프래그먼트로서 동작하지 않는다.
- 프래그먼트 객체가 new 연산자가 아니라 액티비티 위에 올라가야 동작한다.

`MyFragment fragment = new MyFragment();`  
-> 프래그먼트 객체는 만들어졌지만 프래그먼트로 동작하지는 않음

`getSupportFragmentManager().beginTransaction().add(fragment).commit();`  
-> 액티비티에 추가된 후 프래그먼트로 동작함

### 액션바

액션바: 앱의 제목이 보이는 위쪽 부분  

- 옵션 메뉴: 메뉴를 눌렀을 때 나타나는 메뉴  
- 컨텍스트 메뉴: 화면을 길게 누르면 나타나는 메뉴 

`onCreateOptionsMenu()`  
`onCreateContextMenu()`

`onOptionsItemSelected()`

/app/res/menu -> menu resource file

```xml
<menu>
    <item/>
    <item/>
    <item/>
</menu>
```

### 탭 (=Navigation)

```xml
<CoordinatorLayout>
    <AppBarLayout>
        <Toolbar>
        </Toolbar>
        <TabLayout>
        </TabLayout>
    </AppBarLayout>
    <FrameLayout>
    </FrameLayout>
</CoordinatorLayout>
```

### NavigationDrawer

바로가기 메뉴: 화면의 좌측 상단에 위치한 햄버거 모양을 눌렀을 때 나타나는 화면

```xml
<DrawerLayout>
    <CoordinatorLayout>
        <AppBarLayout>
            <Toolbar>
            </Toolbar>
        </AppBarLayout>
        <FrameLayout>
        </FrameLayout>
    </CoordinatorLayout>
    <NavigationView>
    </NavigationView>
</DrawerLayout>
```
## 서비스

서비스: 백그라운드에서 실행되는 앱의 구성 요소

- 서비스 실행  

```
메인 액티비티 -(startService() 메서드 호출)→ *서비스*(onCreate() 호출됨)  
                                              |
/* 서비스는 실행된 상태를 계속 유지하기 위해   비정상 종료(onDestroy() 호출됨)
   서비스가 비정상적으로 종료되더라도            ↓
   시스템이 자동으로 재실행합니다. */         시스템 -자동 재시작→ *서비스*
```

- `startService()` 호출 시 인텐트 객체를 파라미터로 전달  
    - 인텐트 객체는 어떤 서비스를 실행할 것인지에 대한 정보를 담고 있음  
    - 시스템은 서비스를 시작시킨 후 인텐트 객체를 서비스에 전달  
- 서비스가 실행 중이면 `startService()`를 여러 번 호출해도 이미 메모리에 만들어진 상태로 유지  
- 따라서 `startService()`는 *서비스를 시작하는 목적* 이외에 *인텐트를 전달하는 목적*으로 자주 사용
- 인텐트 전달 시 `onCreate()`가 아니라 `onStartCommand()` 실행
    - `onStartCommand()`는 서비스로 전달된 인텐트 객체를 처리  
- 서비스에서 액티비티로 데이터 전달
    - 서비스에서 `startActivity()` 사용
    - FLAG_ACTIVITY_NEW_TASK: 서비스는 화면이 없기 때문에, 화면이 있는 액티비티를 띄우려면 새로운 태스크를 만들어야 한다.
    - FLAG_ACTIVITY_SINGLE_TOP, FLAG_ACTIVITY_CLEAR_TOP: MainActiviy 객체가 이미 메모리에 만들어져 있을 때 재사용
    - MainActivity에서 인텐트 객체 참조 시
        - MainActivity가 메모리에 만들어져 있지 않은 상태: `onCreate()` 호출(`getIntent()`로 전달받음)
        - MainActivity가 메모리에 만들어져 있는 상태: `onNewIntent()` 호출(파라미터로 전달받음)
- 서비스 종료
    - `stopService()` 사용
- 예) 서버에 데이터를 요청하고 응답을 기다리는 네트워킹 작업

## 브로드캐스트 수신자(Broadcast Receiver)

브로드캐스팅(Broadcasting): 메시지를 여러 객체에 전달하는 것  
예) 카카오톡 그룹 채팅방에서 메시지 전달, 안드로이드 앱 구성 요소에 메시지 전달  

브로드캐스트 수신자를 등록하면 액티비티 안에서 브로드캐스트 메시지를 전달받아 다른 작업 수행 가능 

`onReceive()`: 원하는 브로드캐스트 메시지가 도착하면 자동으로 호출됨  

```xml
<manifest>
    <!-- SMS 수신 권한 -->
    <uses-permission android:name="android.permission.RECEIVE_SMS" />
    <application>
        <receiver>
            <intent-filter>
                <action android:name="android.provider.Telephony.SMS_RECEIVED" />
                <!--
                    SMS 메시지가 들어간 인텐트를 구분하기 위한 액션 정보
                    단말에서 SMS를 수신했을 때 이 action 정보가 들어간 인텐트를 전달함
                -->
            </intent-filter>
        </receiver>
    </application>
</manifest>
```
