# LightRefreshLayout
这是一个轻量级的非入侵式的下拉刷新组件。<br/>
本组件借鉴于`SwipeRefreshLayout`，并提供了刷新头部的定制接口，拥有比`SwipeRefreshLayout`更加灵活的定制特性。

## 效果图
### 默认
![default](https://raw.githubusercontent.com/NightFarmer/LightRefreshLayout/master/sample/screenshot/refresh1.gif)
### 自定义示例
![img1](https://raw.githubusercontent.com/NightFarmer/LightRefreshLayout/master/sample/screenshot/refresh5.gif)
![img2](https://raw.githubusercontent.com/NightFarmer/LightRefreshLayout/master/sample/screenshot/refresh4.gif)
![img3](https://raw.githubusercontent.com/NightFarmer/LightRefreshLayout/master/sample/screenshot/refresh3.gif)
![img4](https://raw.githubusercontent.com/NightFarmer/LightRefreshLayout/master/sample/screenshot/refresh2.gif)

## 使用方式

### 加入依赖

### 调整布局代码
只需要在需要刷新的RecyclerView外部包上一层LightRefreshLayout即可。
```xml
    <com.nightfarmer.lightrefreshlayout.LightRefreshLayout
        android:id="@+id/lightRefreshLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <android.support.v7.widget.RecyclerView
            android:id="@+id/recyclerView"
            android:layout_width="match_parent"
            android:layout_height="match_parent"/>

    </com.nightfarmer.lightrefreshlayout.LightRefreshLayout>
```

### 添加刷新监听
拿到LightRefreshLayout对象之后通过setOnRefreshListener来设置监听。
```java
lightRefreshLayout.setOnRefreshListener(new LightRefreshLayout.OnRefreshListener() {
    @Override
    public void onCancel() {
        //刷新被用户通过手势取消时调用
    }

    @Override
    public void onRefresh() {
        //用户拖拽超过阀值并松开时调用
    }
});
```

### 默认头部
`LightRefreshLayout`提供了一个默认样式的刷新头部，当然这个默认的刷新头部可能并不符合你的需求或者审美，那么你可能需要下面的自定义头部，来进行个性定制。

## 自定义刷新头部

`LightRefreshLayout`提供了非常灵活的刷新头部定制接口，使用者可以根据自己的需要来定制任何样式的刷新头部。<br/>
而对于`LightRefreshLayout`来说定制一个刷新头部所要做的事情只有一件：实现一个`LightRefreshHeadProvider`接口
### 接口说明
```java
public interface LightRefreshHeadProvider<T extends LightRefreshHeadProvider.HeadViewHolder> {

    /**
     * 实例化并返回一个头部View的Holder
     * @param parent 父级View，也就是LightRefreshLayout
     * @return HeadViewHolder
     */
    T getHeadView(ViewGroup parent);

    /**
     * 拖拽进度改变时调用，用于更新头部状态
     * @param headHolder 头部viewholder
     * @param progress 拖拽进度，0-1表示没有超过阀值，大于1代表超过阀值
     * @param state 刷新状态，0代表拖拽中，1代表刷新中，2代表刷新完成
     */
    void onProgressChange(T headHolder, float progress, int state);

    class HeadViewHolder {
        public final View headView;
        public HeadViewHolder(View headView) {
            this.headView = headView;
        }
    }
}
```
`LightRefreshProvider`的定义和RecyclerView的Adapter有些类似，通过`getHeadView`方法实例化并获取到头部View的实例，可以使用LayoutInflater来实例化布局文件，也可以直接new出来View对象，这里的View可以是任何类型的，所以使用者可以通过自定义View来实现一些酷炫特效的头部样式。<br/>

`onProgressChange`方法会在会在HeadView被拖拽出的任何状态进行回调，使用者可以根据拖拽的进度以及状态来进行HeadView的文字调整、样式变更、动画播放等处理。

### 设置头部View

通过自定义的`LightRefreshHeadProvider`只需要调用`setHeadViewProvider`方法即可完成刷新头部的切换。
如：<br/>
```java
lightRefreshLayout.setHeadViewProvider(new MyRefreshHeadProvider());
```