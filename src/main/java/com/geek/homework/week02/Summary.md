##### 不同GC算法的对比
		
	GC次数:
		串行GC < CMS GC < 并行GC < G1 GC        1G堆内存
		串行GC < CMS GC < 并行GC < G1 GC        2G堆内存
        
		串行GC < 并行GC < CMS GC < G1 GC        4G堆内存
        
	GC平均耗       
		串行GC > CMS GC > 并行GC > G1 GC        1G堆内存
		串行GC > CMS GC > 并行GC > G1 GC        2G堆内存
		串行GC > CMS GC > 并行GC > G1 GC        4G堆内存
		
	GC总耗时
		串行GC > CMS GC > 并行GC > G1 GC        1G堆内存
		串行GC > CMS GC > 并行GC > G1 GC        2G堆内存
		CMS GC > G1 GC > 串行GC > 并行GC        4G堆内存

#### 压测总结

	RPS(每秒请求数):
    	并行GC > G1 GC > CMS GC > 串行GC        默认堆大小
    	并行GC > 串行GC > CMS GC > G1 G         4g堆大小
    平均响应时间:
    	并行GC < G1 GC < CMS GC < 串行GC        默认堆大小
    	并行GC < G1 GC = 串行GC < CMS GC        4g堆大小

