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
