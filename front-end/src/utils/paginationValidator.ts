// Validation utilities for pagination and API requests
export const paginationValidator = {
  validatePageNumber: (pageNumber: number): number => {
    // Ensure pageNumber is never negative
    if (typeof pageNumber !== 'number' || isNaN(pageNumber)) {
      return 0
    }
    return Math.max(0, Math.floor(pageNumber))
  },

  validatePageSize: (pageSize: number): number => {
    // Ensure pageSize is between 1 and 100
    if (typeof pageSize !== 'number' || isNaN(pageSize)) {
      return 10
    }
    const validated = Math.max(1, Math.floor(pageSize))
    return Math.min(100, validated)
  },

  validatePaginationRequest: (pageNumber: number, pageSize: number, totalPages: number) => {
    const validatedPageNumber = paginationValidator.validatePageNumber(pageNumber)
    const validatedPageSize = paginationValidator.validatePageSize(pageSize)

    // Ensure pageNumber doesn't exceed totalPages
    const finalPageNumber = Math.min(validatedPageNumber, Math.max(0, totalPages - 1))

    return {
      pageNumber: finalPageNumber,
      pageSize: validatedPageSize,
    }
  },
}

export default paginationValidator

