# 订单管理

## 1.订单实现显示（完成）

动态sql思想

## 2.订单编辑（内含编辑页面显示，和修改订单内容）（半完成状态）

订单编辑页面显示包含两个难点，

1. 如何实现动态实时spec和动态实时state

   思路：建立OrderBO，OrderBO里除了基础的数据，还含有动态的Liset<OrderSpecVO>spec,Liset<OrderStateVO>state,

   再分别建立OrderSpecVO，OrderStateVO，记录（待后续）

2. 如何实现规格和状态显示

遍历或者老师上课的方法

`BUG`

- changeOrder 大致完成，但是出现bug请求参数无法进行JSON转换?

是因为编辑页面未完成的原原因，还是代码逻辑错误？

## 3.订单删除(完成)