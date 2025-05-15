import { LucideProps, Printer } from "lucide-react";
import React from "react";

interface ButtonVariantProps {
    handlePress: string;
    loading: boolean;
    text: string;
    Img: React.ForwardRefExoticComponent<Omit<LucideProps, "ref"> & React.RefAttributes<SVGSVGElement>>
}

export const ButtonVariant = ({ loading, handlePress, Img, text, ...props }: any) => {
    return (
        <button
            className="button-print"
            onClick={handlePress}
            disabled={loading}
        >
            <Img className="size-4 mr-2" />
            {text}
        </button>
    )
}