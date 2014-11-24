# 1. Create a sparse bundle image
hdiutil create -ov  -size 700m -type SPARSEBUNDLE -volname "TIBCO Jaspersoft Studio" -fs "Journaled HFS+" -attach jaspersoftstudio.dmg

## 2. Copy the icon of the volume (jssbox.icns)
cp jssbox.icns "/Volumes/TIBCO Jaspersoft Studio/.VolumeIcon.icns"

## 3. Create the application directory
mkdir "/Volumes/TIBCO Jaspersoft Studio/TIBCO Jaspersoft Studio"

## 4. Link the Application directory
ln -s /Applications "/Volumes/TIBCO Jaspersoft Studio/Applications"

## 5. Set the icon for the volume (SetFile is an XCode command)
SetFile -a C "/Volumes/TIBCO Jaspersoft Studio/"

## 6. Set the icon for the Jaspersoft Studio folder... (SetIcon is a self made command)
SetIcon -i jssfolder.icns "/Volumes/TIBCO Jaspersoft Studio/TIBCO Jaspersoft Studio/"

## 7. Copy the background to the volume
cp -Rf jss-package-background.png  "/Volumes/TIBCO Jaspersoft Studio/"

## 8. Copy the license file to the volume License NO longer in the main folder!
#cp LICENSE.txt  "/Volumes/Jaspersoft Studio/"

## 9. Now before unmount everything, use the Finder to customize the look and feel
##    of the folder by setting the background and setting icons size and position.

echo '
   tell application "Finder"
     tell disk "'TIBCO Jaspersoft Studio'"
           open
            set current view of container window to icon view
            set toolbar visible of container window to false
            set statusbar visible of container window to false
            set the bounds of container window to {100, 100, 630, 690}
            set theViewOptions to the icon view options of container window
            set arrangement of theViewOptions to not arranged
            set icon size of theViewOptions to 128
            set background picture of theViewOptions to file "jss-package-background.png"
#            make new alias file at container window to POSIX file "/Applications" with properties {name:"Applications"}
            set position of item "TIBCO Jaspersoft Studio" of container window to {200, 205}
            set position of item "Applications" of container window to {420,205}
#           set position of item "LICENSE.txt" of container window to {300, 450}
           close
           open
           update without registering applications
           delay 5
#           eject
     end tell
   end tell
' | osascript



## 10. Hide the background image...
SetFile -a V "/Volumes/TIBCO Jaspersoft Studio/jss-package-background.png"

## 11. unmount the volume
hdiutil unmount "/Volumes/TIBCO Jaspersoft Studio"

## 12. targz the template...
tar -czvf jss-package-template.tgz jaspersoftstudio.dmg.sparsebundle

