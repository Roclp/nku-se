#ifndef Pivot_Strategies_H
#define Pivot_Strategies_H

#include "line_ptrs.h"
#include "sort_at_adaptor.h"

class Pivot_Strategies {
  public:
	virtual int get_pivot( Sort_AT_Adaptor & , int lo, int hi) =0;
};

class First_Pivot:public  Pivot_Strategies {
  public:
	virtual int get_pivot( Sort_AT_Adaptor & , int lo, int hi);
};

class Random_Pivot:public  Pivot_Strategies {
  public:
	Random_Pivot();
	virtual int get_pivot( Sort_AT_Adaptor & , int lo, int hi);
};

class Median3_Pivot:public  Pivot_Strategies {
  public:
	virtual int get_pivot( Sort_AT_Adaptor & , int lo, int hi);
};

#endif