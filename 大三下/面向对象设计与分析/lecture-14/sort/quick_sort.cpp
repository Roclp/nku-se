#include "stdafx.h"
#include "quick_sort.h"
#include "pivot_strategies.h"

Quick_Sort::Quick_Sort( )
{
	_pivot_strategy = NULL;
}

Quick_Sort::~Quick_Sort( )
{
	delete _pivot_strategy;
}

void Quick_Sort::select_pivot_strategy(Pivot_Strategies * pivot_strategy)
{
	_pivot_strategy = pivot_strategy;
}

void Quick_Sort::sort(Sort_AT_Adaptor & sort_at_adaptor)
{
	quick_sort(sort_at_adaptor, 0, sort_at_adaptor.size()-1 ); 	
}
 
void Quick_Sort::quick_sort(Sort_AT_Adaptor & a, int low, int high)
{
	if ( low >= high ) return;	

	//partitioning into two smaller parts
	Line_Ptrs tmp;
	int pivot_idx = _pivot_strategy->get_pivot(a, low, high);
	tmp = a[low]; a[low]=a[pivot_idx];  a[pivot_idx]=tmp;
	
	int lo = low;
	int hi = high;
	Line_Ptrs pivot_key = a[lo];
	while ( lo < hi ) {	
		while ( lo < hi && pivot_key < a[hi] ) --hi;		
		tmp = a[lo]; a[lo]=a[hi];  a[hi]=tmp;
		while ( lo < hi && a[lo] < pivot_key ) ++ lo;
		tmp = a[lo]; a[lo]=a[hi];  a[hi]=tmp;
	}
	pivot_idx = lo;
	quick_sort( a, low, pivot_idx - 1);
	quick_sort( a, pivot_idx + 1, high);
}

