import { useEffect, useState } from 'react'

/**
 * Hook de debugging pour afficher les requêtes API
 * Utilisé pour diagnostiquer les problèmes de chargement de données
 */
export const useDebugApi = (label: string, data: any, error: any, isLoading: boolean) => {
  useEffect(() => {
    console.log(`[${label}] Loading:`, isLoading)
    if (error) {
      console.error(`[${label}] Error:`, error)
    }
    if (data) {
      console.log(`[${label}] Data:`, data)
    }
  }, [data, error, isLoading, label])
}

