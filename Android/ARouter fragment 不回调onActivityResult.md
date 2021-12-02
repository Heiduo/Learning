解决方法：手动启动

```
Postcard postcard = ARouter.getInstance()
        .build(APath.CURE_ORDER_DETAILS)
        .withObject(Environments.ARG_PARAM1, mAdapter.getData().get(position));

LogisticsCenter.completion(postcard);
Intent intent = new Intent(getActivity(), postcard.getDestination());
intent.putExtras(postcard.getExtras());
startActivityForResult(intent, 100);
```