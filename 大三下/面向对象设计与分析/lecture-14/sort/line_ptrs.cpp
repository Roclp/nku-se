#include "stdafx.h"
#include "line_ptrs.h"
#include "compare_func_factory.h"

int Line_Ptrs::operator < (const Line_Ptrs & r) const
{
	Compare_Func_Factory::Func_Pointer fp;
	fp = Compare_Func_Factory::instance()->get_func_pointer();
	return ( (*fp) (this->_bok, r._bok ) <= 0 );
}
