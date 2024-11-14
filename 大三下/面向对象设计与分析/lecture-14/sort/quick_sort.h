#include "pivot_strategies.h"

class Quick_Sort {
  public:
    Quick_Sort( );
	~Quick_Sort( );
	void select_pivot_strategy(Pivot_Strategies *);
	void sort(Sort_AT_Adaptor & );
  private:
	Pivot_Strategies * _pivot_strategy;
	void quick_sort(Sort_AT_Adaptor &, int lo, int hi);
};