#include "input.h"
#include "sort_at_adaptor.h"
#include "quick_sort.h"

class System_Sort {
  public:
	static System_Sort * instance ( );
	void read_data( );
	void sort( );
	void output_result( );

  private:
    System_Sort( );
    static System_Sort _system_sort;

    Input  input;
	Sort_AT_Adaptor sort_at_adaptor;    
	Quick_Sort quick_sort;
};	