import { clsx, type ClassValue } from "clsx"
import { twMerge } from "tailwind-merge"

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs))
}


export function dateToISO(date: Date) {
  if(isNaN(date.getTime())) {
    return ""
  }
  return date.toISOString().split("T")[0]
}

export function dateToReadable(date: Date) {
  return date.toLocaleDateString()
}