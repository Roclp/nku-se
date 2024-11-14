#ifndef Line_Ptrs_H
#define Line_Ptrs_H

class Line_Ptrs {
  public:
	// Comparison operator used by sort().
	int operator < (const Line_Ptrs &) const;

	// Beginning of line and key (field or column).
	char *_bol,  *_bok;
};

#endif