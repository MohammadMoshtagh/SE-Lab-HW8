# پیمایشگر گراف
در این پروژه، یک پیمایشگر ساده گراف با دو روش اول-سطح و اول-عمق پیاده سازی شده است.


### بخش دوم
#### زیر بخش اول
بررسی دقیق تغییرات می‌پردازیم:

- استفاده از 
`SimpleDirectedGraph`
از کتابخانه‌ی جدید به جای
`SparseMultigraph`

- از توابع پیش‌فرض `outgoingEdgesOf` و `incomingEdgesOf` برای گرفتن یال‌های خروجی و ورودی یک راس و نهایتا پیدا کردن همسایگان آن‌ها استفاده می‌کنیم.
- همچنین یال‌ها در این حالت جدید از نوع رشته هستند.


#### زیر بخش دوم
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