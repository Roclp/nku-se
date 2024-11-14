// sort.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "options.h"
#include "system_sort.h"

int main(int argc, char**argv)
{
	Options::instance()->parse_args (argc, argv) ;
	System_Sort::instance()->read_data();
    System_Sort::instance()->sort();
	System_Sort::instance()->output_result();
	return 0;
}

