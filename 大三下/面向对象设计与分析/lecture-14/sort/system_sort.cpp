#include "stdafx.h"
#include <iostream>
#include "system_sort.h"
#include "Options.h"
#include "compare_func_factory.h"
#include "pivot_strategies.h"

System_Sort  System_Sort::_system_sort;

System_Sort::System_Sort( )
{
}

System_Sort * System_Sort::instance ( )
{
	return & _system_sort;
}

void System_Sort::read_data( )
{
	input.read( Options::instance()->file_name() );	
	sort_at_adaptor.make_table( input.buffer(), input.num_lines() );
}

void System_Sort::sort( )
{	
	Compare_Func_Factory::instance()->set_func_pointer();

	switch ( Options::instance()->pivot_strat() ) {
	  case Options::FIRST:
        quick_sort.select_pivot_strategy( new First_Pivot() );
		break;
	  case Options::RANDOM:
        quick_sort.select_pivot_strategy( new Random_Pivot() );
		break;
	  case Options::MEDIAN3:
        quick_sort.select_pivot_strategy( new Median3_Pivot() );
		break;
	}
	
	quick_sort.sort( sort_at_adaptor );
}

void System_Sort::output_result( )
{
	std::cout << sort_at_adaptor;
}
