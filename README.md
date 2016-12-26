# android-map-scrollview

本工程基于高德地图android SDK封装，结合地图TextureSupportMapFragment、ViewPager，响应滑动手势。

## 前述 ##
- [高德官网申请Key](http://lbs.amap.com/dev/#/).
- 阅读[地图参考手册](http://a.amap.com/lbs/static/unzip/Android_Map_Doc/index.html).
- 工程基于Android 地图SDK实现

## 扫一扫安装##
![Screenshot](https://github.com/amap-demo/android-map-scrollview/blob/master/resource/download.png?raw=true)

## 效果展示##

![Screenshot](https://github.com/amap-demo/android-map-scrollview/blob/master/resource/screenshot.png?raw=true)

## 使用方法##
###1:配置搭建AndroidSDK工程###
- [Android Studio工程搭建方法](http://lbs.amap.com/api/android-sdk/guide/creat-project/android-studio-creat-project/#add-jars).
- [通过maven库引入SDK方法](http://lbsbbs.amap.com/forum.php?mod=viewthread&tid=18786).



 
## 核心类/接口 ##
| 类    | 接口  | 说明   | 版本  |
| -----|:-----:|:-----:|:-----:|
|MapsInitializer|initialize(Context paramContext)|初始化高德地图Android API,为使用它包含的类做准备。 在这些类中，您如果正在使用MapFragment或MapView，并且调用getMap()获得非空的地图，您可以不需要调用这个方法。|V2.0.2|
|TextureSupportMapFragment|newInstance()|使用默认的选项创建TextureSupportMapFragment|V3.1.0|

## 核心难点 ##
 - 重写ViewPager canScroll方法，解决ViewPager和地图横向滑动冲突
```java
public class ScrollViewPager extends ViewPager {

    public ScrollViewPager(Context context) {
        super(context);
    }

    public ScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        if (Math.abs(dx) > 50) {
            return super.canScroll(v, checkV, dx, x, y);
        } else {
            return true;
        }
    }
}

```

- 动态初始化TextureSupportMapFragment

```java
        
        //Tab 地图
        try {
            MapsInitializer.initialize(this.getApplicationContext());  //初始化TextureSupportMapFragment需要先设置context
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        mMapFragment = TextureSupportMapFragment.newInstance();
        mFragments.add(mMapFragment);
```
