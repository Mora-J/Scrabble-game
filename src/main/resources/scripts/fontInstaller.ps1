# Comprobar si se está ejecutando con privilegios de administrador
if (-not ([Security.Principal.WindowsPrincipal] [Security.Principal.WindowsIdentity]::GetCurrent()).IsInRole([Security.Principal.WindowsBuiltInRole] "Administrator"))
{
    # Crear un nuevo proceso con privilegios de administrador
    $newProcess = New-Object System.Diagnostics.ProcessStartInfo "PowerShell";
    $newProcess.Arguments = "-NoProfile -ExecutionPolicy Bypass -File `"$PSCommandPath`"";
    $newProcess.Verb = "runas";
    [System.Diagnostics.Process]::Start($newProcess) | Out-Null;
    exit;
}

# Cambiar la política de ejecución a Bypass temporalmente
Set-ExecutionPolicy Bypass -Scope Process -Force

# Obtener la ruta del directorio actual donde se encuentra el script
$scriptPath = Split-Path -Parent $MyInvocation.MyCommand.Definition

# Ruta a la fuente que quieres instalar (relativa al directorio del script)
$relativeSourcePath = "..\fonts\arcade.otf"

# Combinar la ruta del script con la ruta relativa de la fuente
$sourcePath = Join-Path -Path $scriptPath -ChildPath $relativeSourcePath

# Ruta a la carpeta de fuentes del sistema
$fontsFolder = "$Env:SystemRoot\Fonts"

# Obtener el nombre de la fuente
$fontName = [System.IO.Path]::GetFileNameWithoutExtension($sourcePath)

# Comprobar si la fuente ya está instalada
$installedFonts = (New-Object System.Drawing.Text.InstalledFontCollection).Families
$fontInstalled = $installedFonts.Name -contains $fontName

if ($fontInstalled) {
    Write-Output "La fuente '$fontName' ya está instalada."
} else {
    # Copiar la fuente a la carpeta de fuentes del sistema
    Copy-Item $sourcePath -Destination $fontsFolder

    # Registrar la fuente en el sistema
    Add-Type -TypeDefinition @"
using System;
using System.Runtime.InteropServices;

public class FontInstaller {
    [DllImport("gdi32.dll")]
    public static extern int AddFontResource(string lpFileName);
}
"@
    [FontInstaller]::AddFontResource("$fontsFolder\$fontName")

    Write-Output "La fuente '$fontName' ha sido instalada."
}

# Restaurar la política de ejecución predeterminada (Restricted)
Set-ExecutionPolicy Restricted -Scope Process -Force
