# پیمایشگر گراف
در این پروژه، یک پیمایشگر ساده گراف با دو روش اول-سطح و اول-عمق پیاده سازی شده است.



### بخش سوم
#### استفاده از این الگو به چه علتی قابل قبول است؟
روش های مختلفی برای پیاده‌سازی پیمایش گراف وجود دارد. با این الگو امکان توسعه‌ی بیشتر و افزودن الگوریتم‌های دیگر برای پیمایش گراف به راحتی وجود دارد. کافیست کلاس جدیدی بسازیم که از اینترفیس 
`Traverser`
ارث‌بری کند و پیاده‌سازی دلخواهمان را در آن انجام دهیم.
```java
public class CustomTraverser implements Traverser {
    private final GraphAdapter<Integer, String> graph;

    public CustomTraverser(GraphAdapter<Integer, String> graph) {
        this.graph = graph;
    }
    
    @Override
    List<Integer> traverse(Integer startVertex);
}
```

### روش تحقق این الگو را به صورت مختصر توضیح دهید.
همانطور که بالاتر توضیح دادیم کافیست اینترفیس 
`traverser`
ایمپلیمنت شود و سپس با پیاده‌سازی تابع 
`traverse`
که شماره‌ی یک راس شروعی را ورودی می‌گیرد و از آن راس پیمایش را انجام می‌دهد و بر حسب الگوریتم مورد استفاده لیست راس‌های ویزیت‌شده را خروجی می‌دهد، کار انجام شده است.
سپس می‌توان از این کلاس جدید ایستنس گرفت و از آن با خوشحالی استفاده کرد.

```java
Traverser customTraverser = new CustomTraverse(graph);

List<Integer> traversedPath = customTraverser.traverse(s_index);

System.out.println(traversedPath);
```