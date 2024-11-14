#include "stdafx.h"
#include "time.h"
#include "pivot_strategies.h"

int First_Pivot::get_pivot( Sort_AT_Adaptor & sort_at_adaptor, int lo, int hi)
{
	return lo;
}


Random_Pivot::Random_Pivot()
{
	srand( (unsigned)time( NULL ) );	
}

int Random_Pivot::get_pivot( Sort_AT_Adaptor & sort_at_adaptor, int lo, int hi)
{  
	int idx = (double)rand() /RAND_MAX * ( hi - lo ) + lo;
	return idx;
}

int Median3_Pivot::get_pivot( Sort_AT_Adaptor & a, int low, int high) 
{
	int mid = (low + high ) /2;

    if ( a[low] < a[mid] ) {
		if ( a[mid] < a[high] ) 
			return mid;
		else
			if (a[low] < a[high])
				return high;
			else
				return low;
	}else{		
		if ( a[mid] < a[high] ) {
			if ( a[low] < a[high])
				return low;
			else
				return high;
		} else
			return mid;
	}
}