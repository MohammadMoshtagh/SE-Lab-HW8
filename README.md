[Kanban](https://github.com/users/MohammadMoshtagh/projects/5)

# پیمایشگر گراف
در این پروژه، یک پیمایشگر ساده گراف با دو روش اول-سطح و اول-عمق پیاده سازی شده است.

## بخش اول

### زیر بخش اول

در این پیاده‌سازی، ما الگوی 
Adapter 
را به صورت 
Object Scope 
استفاده کردیم. به طور کلی دلیل اینکه از این حالت استفاده کردیم، این است که به جای وراثت
(Inheritance)
از ترکیب
(Composition)
برای تطبیق رابطه‌ها و وابستگی‌ها استفاده می‌کند.
دلایل مفصل‌تر ما برای این انتخاب را می‌توانید در ادامه مشاهده کنید:

۱. 
در 
Object Scope، 
آداپتر می‌تواند چندین نمونه از کلاس‌های مختلف را ترکیب کند و رفتارهای متنوعی را پیاده‌سازی کند. در مثال داده شده هم ما دو پیاده‌سازی داشتیم و با استفاده از Object Scope می‌توانیم هر دو را داشته باشیم و بدون نیاز به تغییر در کد سرویس‌هایی که به آن وابسته هستند، آداپتر مورد نظر را تغییر دهیم و از لایبرری‌های متفاوت استفاده کنیم. 

۲. 
با استفاده از این روش می‌توانیم آداپتری پیاده‌سازی کنیم که از ترکیب چند لایبرری مختلف استفاده می‌کند و این موضوع یکی از نشانه‌های سادگی توسعه در حالت استفاده از 
Object Scope 
است.

۳.
در این روش چون صرفا از کتابخانه خارجی برای پیاده‌سازی آداپتر استفاده کردیم و فرآیندی به پیچیدگی ارث‌بری نداریم، می‌توانیم در صورت تغییر یا بروزرسانی در کتابخانه به راحتی با تغییر در آداپتر، پیاده‌سازی خود را با این تغییر سازگار کنیم.

از معایب استفاده از 
Class Scope 
هم می‌توانیم به موارد زیر اشاره کنیم.

۱.
در Class Scope، آداپتر باید از کلاس هدف ارث‌بری کند. اگر کلاس هدف قبلاً از کلاسی دیگر ارث‌بری کرده باشد (چندسطحی یا کلاس‌های نهایی)، محدودیت‌های وراثت ممکن است مشکل‌ساز شوند.

۲. 
همانطور که در مزایای 
Object Scope
هم ذکر کردیم، استفاده از Class Scope 
ممکن است باعث ایجاد پیچیدگی شود و فرآیند توسعه و نگهداری را سخت‌تر می‌کند.

۳. 
از آنجا که این حالت از پیاده‌سازی الگوی Adapter
فقط برای زبان‌هایی که از ارث‌بری چندگانه پشتیبانی می‌کنند کاربرد دارد و جاوا از چنین قابلیتی پشتیبانی نمی‌کند، پس حالت 
Class Scope
نمی‌تواند انتخاب معقولی برای ما باشد.

### زیر بخش دوم

برای پیاده‌سازی الگوی 
Adapter
در حالت 
Object Scope، مراحل زیر را طی کردیم:

۱. 
ابتدا اینترفیسی به نام 
`GraphAdapter`
ایجاد کردیم، تا به ازای هر کتابخانه‌ای که نیاز داشتیم در زمینه گراف‌ها استفاده کنیم، یک پیاده‌سازی از این اینترفیس انجام دهیم. 

۲.
سپس برای `SparseMultigraph`، یک کلاس ساختیم که اینترفیس 
`GraphAdapter`
را پیاده‌سازی می‌کند و در آن برای پیاده‌سازی قابلیت‌هایی که از گراف انتظار داریم (اضافه کردن راس و یال و گرفتن همسایه‌های یک راس)
از یک آبجکت از جنس
`SparseMultigraph`
استفاده کردیم که آن را در کانستراکتور دریافت می‌کنیم یا همان‌جا می‌سازیم.

۳.
در گام بعدی به جای اینکه در کلاس‌های وابسته به
`SparseMultigraph`
مانند
`BfsGraphTraverser`،
مستقیما از
`SparseMultigraph`
استفاده کنیم، از 
Adapterای
که پیاده‌سازی کردیم استفاده می‌کنیم.

۴. 
در گام آخر نیاز داریم تا در هنگام ساخت دو کلاس Traverser
در Main
به جای `SparseMultigraph`، یک بچه از Adapterای که پیاده‌سازی کردیم را ورودی بدهیم.

۵. 
در گام آخر هم برای اینکه کتابخانه را تغییر بدهیم، به راحتی یک کلاس 
Adapter
دیگر می‌سازیم که 
`GraphAdapter`
را پیاده‌سازی می‌کند و در آن این دفعه از `SimpleDirectedGraph` استفاده می‌کنیم.
در نهایت برای اینکه کلاس‌های Traverser هم از این Adapter جدید استفاده کنند، کافی است در Main و در هنگام Initialize شدن آن‌ها Adapter مربوط به `SimpleDirectedGraph` را ورودی بدهیم.

## بخش دوم

### زیر بخش اول

در ادامه به بررسی دقیق تغییرات می‌پردازیم:

۱. استفاده از کتابخانه‌ی جدید
`SimpleDirectedGraph` 
به جای
`SparseMultigraph`

۲. از توابع پیش‌فرض `outgoingEdgesOf` و `incomingEdgesOf` برای گرفتن یال‌های خروجی و ورودی یک راس و نهایتا پیدا کردن همسایگان آن‌ها استفاده می‌کنیم.

۳. همچنین یال‌ها در این حالت جدید از نوع رشته هستند.


### زیر بخش دوم

تغییر کتابخانه از
`JUNG`
به
`JGraphT`
تنها روی کلاس اداپتور تاثیر دارد و بر باقی پروژه تاثیرگذار نیست.
با استفاده از این کتابخانه به راحتی می‌توانیم توابع مورد نیاز برای پیاده‌سازی عملیات‌های لازم در گراف را پیاده‌سازی و داشته باشیم.

```java
package org.example.graphTravelers.adapter;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.HashSet;
import java.util.Set;

public class SparseMultigraphAdapter<V, E> implements GraphAdapter<V, E> {

    private final DefaultDirectedGraph<V, DefaultEdge> graph;

    public SparseMultigraphAdapter() {
        this.graph = new DefaultDirectedGraph<>(DefaultEdge.class);
    }

    @Override
    public void addVertex(V vertex) {
        this.graph.addVertex(vertex);
    }

    @Override
    public void addEdge(E edge, V sourceVertex, V targetVertex) {
        this.graph.addEdge(sourceVertex, targetVertex);
    }

    @Override
    public Set<V> getNeighbors(V vertex) {
        Set<V> neighbors = new HashSet<>();
        for (V target : graph.vertexSet()) {
            if (graph.containsEdge(vertex, target)) {
                neighbors.add(target);
            }
        }
        return neighbors;
    }
}
```

## بخش سوم

### استفاده از این الگو به چه علتی قابل قبول است؟

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

## روش تحقق این الگو را به صورت مختصر توضیح دهید.

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
